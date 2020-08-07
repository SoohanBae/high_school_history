const express = require("express");
const router = express.Router();
const path = require("path");
// const fs = require('fs');
const con = require("../../db/db");
const util = require("../../check/util");

const { isLoggedIn } = require("../../check/check");

router.get("/list", isLoggedIn, (req, res, next) => {
    userNo = req.user;
    console.log(userNo);
    con.query(
        "select u.classCode, c.className, " +
            "(select count(*) from exam e where e.examNo not in(select r.examNo from result r where r.userNo = ?)" +
            "and e.classCode = c.classCode) noExamCount from classUser u,class c where u.userNo = ? and c.classCode = u.classCode order by noExamCount desc;",
        [userNo, userNo],
        (err, result, fields) => {
            if (err) {
                //에러체크
                return res
                    .status(400)
                    .json(util.successFalse(err, "검색 실패"));
            }

            if (result && result.length != 0) {
                //result 결과값이 있으면

                console.log(result);
                return res.status(200).json(util.successTrue(result));
            } else {
                return res.sendStatus(204);
            }
        }
    );
});

router.post("/", isLoggedIn, (req, res, next) => {
    userNo = req.user;
    const { classCode } = req.body;

    con.query(
        "select * from classUser where classCode = ? and userNo = ?",
        [classCode, userNo],
        (err, result, fields) => {
            if (err) {
                //에러체크
                return res
                    .status(400)
                    .json(util.successFalse(err, "검색 실패"));
            }

            if (result && result.length == 0) {
                con.query(
                    "insert into classUser values('0',?,?);",
                    [classCode, userNo],
                    (err2, result2, fields2) => {
                        if (err2) {
                            //에러체크
                            return res
                                .status(400)
                                .json(util.successFalse(err2, "입력 실패"));
                        }

                        if (result2 && result2.length != 0) {
                            //result 결과값이 있으면

                            console.log(result2);
                            return res
                                .status(200)
                                .json(util.successTrue(result2));
                        } else {
                            return res.sendStatus(204);
                        }
                    }
                );
            } else {
                return res.sendStatus(204);
            }
        }
    );
});

router.options("/list", (req, res) => {
    res.sendStatus(200);
});

router.all("/list", (req, res, next) => {
    return res
        .status(405)
        .json(util.successFalse(null, "요청 메서드를 확인하세요"));
});

router.options("/", (req, res) => {
    res.sendStatus(200);
});

router.all("/", (req, res, next) => {
    return res
        .status(405)
        .json(util.successFalse(null, "요청 메서드를 확인하세요"));
});

module.exports = router;
