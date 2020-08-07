const passport = require("passport");
const crypto = require("crypto");
const LocalStrategy = require("passport-local").Strategy;
const con = require("../db/db");
var jwt = require("jwt-simple");
const util = require("../check/util");

module.exports = () => {
    passport.serializeUser((user, done) => {
        //성공 시 호출
        console.log("성공시" + user.userNo); // 출력 테스트 완료!
        done(null, user.userNo); //여기의 user가 deserializeUser의 첫번째 매개변수로 이동
    });

    passport.deserializeUser((userNumber, done) => {
        //매 요청시 실행, passport.session() 미들웨어가 이 메서드 호출
        console.log("deserial" + userNumber); // 출력 테스트 완료!
        let q = "select * from user where userNo='" + userNumber + "'";
        con.query(q, (err, result, fields) => {
            if (!err) done(null, userNumber);
        });
        // done(null, user);
    });

    passport.use(
        "user",
        new LocalStrategy(
            {
                usernameField: "userEmail",
                passwordField: "userPw",
                session: true,
                passReqToCallback: false
            },
            (userEmail, userPw, done) => {
                con.query(
                    "select userSalt from user where userEmail=?",
                    [userEmail],
                    (err, result, fields) => {
                        const loginsalt =
                            result[0] !== undefined ? result[0].userSalt : "0";
                        //const loginsalt = result[0].userSalt;
                        console.log("loginSalt:", loginsalt);

                        crypto.pbkdf2(
                            userPw,
                            loginsalt,
                            12653,
                            64,
                            "sha512",
                            (err, key) => {
                                const loginPw = key.toString("base64");
                                console.log("loginPw:", loginPw);

                                con.query(
                                    "select * from user where userEmail=? and userPw=?",
                                    [userEmail, loginPw],
                                    (err, result, fields) => {
                                        if (err) return done(err);
                                        else if (result == "") {
                                            console.log(
                                                "아이디 또는 비밀번호가 틀렸습니다."
                                            ); //출력 테스트 완료!
                                            done(null, false, {
                                                message:
                                                    "아이디 또는 비밀번호가 틀렸음"
                                            });
                                        } else {
                                            let data = result[0];
                                            return done(null, data);
                                        }
                                    }
                                );
                            }
                        );
                    }
                );
            }
        )
    );
};