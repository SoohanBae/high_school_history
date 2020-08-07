<%@ page contentType="text/html; charset=utf-8"%><!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html: charset=utf-8">
    <title>1부터 10까지 순서대로</title>
</head>
<body>
    <h3>1부터 10까지 순서대로</h3>
    <% 
    for(int cnt=1;cnt<=10;cnt++){ 
        out.print(cnt + "<br>");
    } 
    %>

</body>
</html>
