const router = require("express").Router();
const multer = require("multer");
const con = require("../../db/db");
const path = require("path");
const fs = require("fs");
const crypto = require("crypto");
const passport = require("passport");
const { isLoggedIn, isNotLoggedIn } = require("../../check/check");

const util = require("../../check/util");

var upload = multer({
    storage: multer.diskStorage({
        //서버 디스크에 저장
        destination(req, file, cb) {
            cb(null, "profileImage/"); //프사 저장 경로
        },
        filename(req, file, cb) {
            const ext = path.extname(file.originalname); //파일의 확장자를 ext에 저장
            cb(
                null,
                path.basename(file.originalname, ext) +
                    new Date().valueOf() +
                    ext
            ); //파일이름+업로드날짜+확장자
        }
    })
});

router.post("/login", isNotLoggedIn, (req, res, next) => {
    passport.authenticate("user", (authError, user, info) => {
        if (authError) {
            console.error(authError);
            return next(authError);
        }
        if (!user) {
            req.flash("loginError", info.message);
            return res.status(204).json(util.successFalse(null, info.message));
        }
        console.log("성공");
        console.log(user);
        return req.login(user, loginError => {
            if (loginError) {
                console.error(loginError);
                return next(loginError);
            }

            let explicitUserData = user;
            delete explicitUserData.userNo;
            delete explicitUserData.userPw;
            delete explicitUserData.userSalt;
            //delete explicitUserData.token;

            //리다이렉트를 하면안됨
            return res.json(util.successTrue(explicitUserData));
        });
    })(req, res, next);
});

router.get("/loginCheck", isLoggedIn, (req, res) => {
    userNo = req.user;
    console.log(userNo);
    con.query(
        "select userName, userEmail, userType from user where userNo = ?;",
        [userNo],
        (err, result, fields) => {
            if (err) {
                //에러체크
                return res
                    .status(400)
                    .json(util.successFalse(err, "검색 실패"));
            }

            if (result && result.length != 0) {
                return res.status(200).json(util.successTrue(result[0]));
            } else {
                return res.sendStatus(204);
            }
        }
    );
});

router.get("/logout", isLoggedIn, (req, res) => {
    req.logout();
    req.session.destroy();
    res.status(200).json(util.successTrue("로그아웃 성공"));
});

router.post(
    //프사는 profileImage폴더에 파일이름+업로드날짜+확장자 로 저장한다.
    "/signup",
    // isNotLoggedIn,
    (req, res, next) => {
        const { userEmail, userName, userPw, userType } = req.body;
        console.log(req.body);

        let signPw = undefined;
        let signsalt = undefined;

        crypto.randomBytes(64, (err, buf) => {
            //pw단방향 암호화
            signsalt = buf.toString("base64");
            console.log("salt: " + signsalt);
            crypto.pbkdf2(userPw, signsalt, 12653, 64, "sha512", (err, key) => {
                signPw = key.toString("base64");
                console.log("signPw: " + signPw);
                // let q1 = "select userEmail from user where userName=" + userEmail;
                con.query(
                    "select userEmail from user where userEmail=?",
                    [userEmail],
                    (err, result, fields) => {
                        if (result && result.length != 0) {
                            return res
                                .status(400)
                                .json(
                                    util.successFalse(
                                        null,
                                        "이미있는 아이디입니다."
                                    )
                                );
                        }
                        con.query(
                            "insert into user values('0',?,?,?,?,?)",
                            [userName, userEmail, signPw, userType, signsalt],
                            (err, result, fields) => {
                                if (err) {
                                    console.log(err);
                                    return res
                                        .status(400)
                                        .json(
                                            util.successFalse(err, "입력 실패")
                                        );
                                }

                                if (result && result.length != 0) {
                                    console.log(result);
                                    return res
                                        .status(201)
                                        .json(util.successTrue(result));
                                } else {
                                    return res.sendStatus(204);
                                }
                            }
                        );
                    }
                );
            });
        });
    }
);

router.delete("/exit", (req, res, next) => {
    const userNo = req.user.userNo;

    let q = "delete from user where userNo =" + userNo;
    console.log(q);
    con.query(q, (err, result, fields) => {
        if (err) {
            //에러체크
            return res.status(400).json(util.successFalse(err, "삭제 실패"));
        }

        if (result && result.length != 0) {
            //result 결과값이 있으면

            console.log(result);
            return res.status(200).json(util.successTrue(result));
        } else {
            return res.sendStatus(204);
        }
    });
});

router;

router.options("/login", (req, res) => {
    res.sendStatus(200);
});

router.options("/logout", (req, res) => {
    res.sendStatus(200);
});

router.options("/signup", (req, res) => {
    res.sendStatus(200);
});

router.options("/exit", (req, res) => {
    res.sendStatus(200);
});

router.all("/login", (req, res, next) => {
    return res
        .status(405)
        .json(util.successFalse(null, "요청 메서드를 확인하세요"));
});

router.all("/logout", (req, res, next) => {
    return res
        .status(405)
        .json(util.successFalse(null, "요청 메서드를 확인하세요"));
});
router.all("/signup", (req, res, next) => {
    return res
        .status(405)
        .json(util.successFalse(null, "요청 메서드를 확인하세요"));
});
router.all("/exit", (req, res, next) => {
    return res
        .status(405)
        .json(util.successFalse(null, "요청 메서드를 확인하세요"));
});

module.exports = router;
