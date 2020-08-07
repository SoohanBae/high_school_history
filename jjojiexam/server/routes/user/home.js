const express = require("express");
const router = express.Router();
const path = require("path");
// const fs = require('fs');
const con = require("../../db/db");
const util = require("../../check/util");

const { isLoggedIn } = require("../../check/check");

router.get("/userCount", isLoggedIn, (req, res, next) => {
    userNo = req.user;
    console.log(userNo);
    con.query(
        "select u2.userName,(select count(*) " +
            "from exam e1 " +
            "where e1.examNo not in (select r1.examNo from result r1 where r1.examNo = e1.examNo and r1.userNo = u2.userNo) " +
            "and e1.classCode in (select classCode from classUser c where c.userNo = u2.userNo)) notEndCount, " +
            "(select count(distinct e1.examNo) " +
            "from exam e1, result r1 " +
            "where e1.examNo = r1.examNo and e1.classCode in (select classCode from classUser c where c.userNo = u2.userNo) and r1.userNo = u2.userNo) endCount, " +
            "( select " +
            "avg((select count(*) from exam e, examQ q, result r, resultA a where e.examNo = r.examNo and r.resultNo = a.resultNo and q.examNo = e.examNo and q.examQNumber = a.resultANumber and q.examARight = a.examARight and r.userNo = r1.userNo and r.resultNo = r1.resultNo) " +
            "/(select count(*) from examQ q where q.examNo = e1.examNo)*100) scoreAvg " +
            "from exam e1, result r1, class c " +
            "where e1.examNo = r1.examNo and c.classCode = e1.classCode and r1.userNo = u2.userNo " +
            ") scoreAvg, u2.userEmail " +
            "from user u2 " +
            "where u2.userNo = ?;",
        [userNo],
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
                return res.status(200).json(util.successTrue(result[0]));
            } else {
                return res.sendStatus(204);
            }
        }
    );
});

router.get("/event/list", isLoggedIn, (req, res, next) => {
    userNo = req.user;
    console.log(userNo);
    con.query("select * from event", (err, result, fields) => {
        if (err) {
            //에러체크
            return res.status(400).json(util.successFalse(err, "검색 실패"));
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

router.options("/event/list", (req, res) => {
    res.sendStatus(200);
});

router.all("/event/list", (req, res, next) => {
    return res
        .status(405)
        .json(util.successFalse(null, "요청 메서드를 확인하세요"));
});

router.options("/userCount", (req, res) => {
    res.sendStatus(200);
});

router.all("/userCount", (req, res, next) => {
    return res
        .status(405)
        .json(util.successFalse(null, "요청 메서드를 확인하세요"));
});

module.exports = router;
