const mysql = require("mysql");

const con = mysql.createConnection({
    host: "127.0.0.1",
    user: "root",
    password: "1234",
    database: "jjoji",
    charset: "utf8"
});
con.connect(function(err) {
    if (err) throw err;
    console.log("ok");
});

module.exports = con;
