<%@pagecontentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>인사하기</title>
</head>
<body>
    안녕하세요
    <%= request.getParameter("YOURNAME") %> 님
</body>
</html>