const createError = require("http-errors");
const express = require("express");
const morgan = require("morgan");
const path = require("path");
const session = require("express-session");
const cookieParser = require("cookie-parser");
const nowTime = require("date-utils");
const passport = require("passport");
const flash = require("connect-flash");
const util = require("./check/util");
require("dotenv").config();
//---------------------router----------------------
const userRouter = require("./routes/user/user");
const classRouter = require("./routes/user/class");
const examRouter = require("./routes/user/exam");
const homeRouter = require("./routes/user/home");
const answerRouter = require("./routes/user/answer");

const passportUserConfig = require("./passport/passport_user");
//const passportAdminConfig = require("./passport/passport_admin");

//---------------------router------------------------

const app = express();
passportUserConfig();
//passportAdminConfig();
//passport 내부의 코드를 실행하기 위해
app.set("port", process.env.PORT || 3000);

app.use(morgan("dev"));
app.use(express.json());
app.use(
    express.urlencoded({
        extended: false
    })
);

app.use(cookieParser(process.env.COOKIE_SECRET));
app.use(
    session({
        name: "sessionID",
        resave: false,
        saveUninitialized: true,
        secret: process.env.COOKIE_SECRET,
        cookie: {
            httpOnly: true,
            secure: false
        }
    })
);
app.use(flash());
app.use(passport.initialize()); //요청 객체에 passport설정을 심는다
app.use(passport.session()); //express-session에서 생성하는 것이므로 express-session 뒤에 연결 해야 된다

// app.all('/*', function (req, res, next) {
//     res.header("Access-Control-Allow-Origin", "*");
//     res.header("Access-Control-Allow-Headers", "Content-Type");
// });

//미들웨어 설정
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header(
        "Access-Control-Allow-Methods",
        "GET, POST, PATCH, PUT, DELETE, OPTIONS"
    );
    res.header(
        "Access-Control-Allow-Headers",
        "Origin, Content-Type, X-Auth-Token, Authorization"
    );
    //res.header("Access-Control-Allow-Headers", "content-type");
    res.header("X-Content-Type-Options", "nosniff");
    res.header("X-Frame-Options", "deny");
    // res.header("Content-Security-Policy", "default-src 'none'");
    res.removeHeader("x-Powered-By");
    //res.header("Content-Type", "application/json; charset=utf-8");

    next();
});
app.use("/answer", answerRouter);
app.use("/home", homeRouter);
app.use("/user", userRouter);
app.use("/class", classRouter);
app.use("/exam", examRouter);
//app.use("/")
//---------------------router------------------------

app.use((req, res, next) => {
    const err = new Error("Not Found");
    err.status = 404;
    return res.status(404).json(util.successFalse(err, "파일 찾을 수 없음"));
});

app.use((err, req, res) => {
    res.locals.message = err.message;
    res.locals.error = req.app.get("env") === "development" ? err : {};
    res.status(err.status || 500);
    res.render("error");
});

app.listen(app.get("port"), () => {
    console.log(app.get("port"), "번 포트에서 대기 중");
});
