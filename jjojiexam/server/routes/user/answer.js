const express = require("express");
const router = express.Router();
const path = require("path");
// const fs = require('fs');
const con = require("../../db/db");
const util = require("../../check/util");

const { isLoggedIn } = require("../../check/check");

router.get("/exam/list", isLoggedIn, (req, res, next) => {
    userNo = req.user;
    const { examNo } = req.query;

    con.query(
        "select * from examQ q left join examA a on q.examQNo = a.examQNo where q.examNo = ? order by q.examQNo, a.examANumber;",
        [examNo],
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

                const merged = result.reduce(
                    (
                        r,
                        {
                            examQNo,
                            examQNumber,
                            examQName,
                            examQImage,
                            examARight,
                            examNo,
                            ...rest
                        }
                    ) => {
                        const key = `${examQNo}-${examQNumber}-${examQName}-${examQImage}-${examARight}-${examNo}`;
                        r[key] = r[key] || {
                            examQNo,
                            examQNumber,
                            examQName,
                            examQImage,
                            examARight,
                            examNo,
                            examASlot: []
                        };
                        r[key]["examASlot"].push(rest);
                        return r;
                    },
                    {}
                );

                const reData = Object.values(merged);
                console.log(reData);

                return res.status(200).json(util.successTrue(reData));
            } else {
                return res.sendStatus(204);
            }
        }
    );
});

router.post("/", isLoggedIn, (req, res, next) => {
    userNo = req.user;
    const { examNo, useTime, answerData } = req.body;
    //new Date().toFormat("YYYY-MM-DD HH24:MI:SS")

    con.query(
        "insert into result values('0',?,?,?,?);",
        [examNo, userNo, new Date().toFormat("YYYY-MM-DD HH24:MI:SS"), useTime],
        (err, result, fields) => {
            if (err) {
                //에러체크
                return res
                    .status(400)
                    .json(util.successFalse(err, "검색 실패"));
            }

            if (result && result.length != 0) {
                con.query(
                    "select resultNo from result order by resultNo desc limit 1;",
                    (err2, result2, fields2) => {
                        if (err2) {
                            //에러체크
                            return res
                                .status(400)
                                .json(util.successFalse(err2, "검색 실패"));
                        }

                        if (result2 && result2.length != 0) {
                            //result 결과값이 있으면

                            var resultNo = result2[0].resultNo;

                            var a = false;
                            var q = "";
                            for (var i in answerData) {
                                if (a) {
                                    q +=
                                        ",('0','" +
                                        resultNo +
                                        "','" +
                                        answerData[i].resultANumber +
                                        "','" +
                                        answerData[i].examARight +
                                        "')";
                                } else {
                                    q =
                                        "insert into resultA values('0','" +
                                        resultNo +
                                        "','" +
                                        answerData[i].resultANumber +
                                        "','" +
                                        answerData[i].examARight +
                                        "')";
                                    a = true;
                                }
                            }
                            console.log(q);
                            con.query(q, (err3, result3, fields3) => {
                                if (err3) {
                                    //에러체크
                                    return res
                                        .status(400)
                                        .json(
                                            util.successFalse(err3, "입력 실패")
                                        );
                                }

                                if (result3 && result3.length != 0) {
                                    return res
                                        .status(200)
                                        .json(util.successTrue(result3));
                                } else {
                                    return res.sendStatus(204);
                                }
                            });
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

router.options("/notEnd/list", (req, res) => {
    res.sendStatus(200);
});

router.all("/notEnd/list", (req, res, next) => {
    return res
        .status(405)
        .json(util.successFalse(null, "요청 메서드를 확인하세요"));
});

module.exports = router;
