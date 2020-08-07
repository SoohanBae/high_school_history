<%@pagecontentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>1부터 100까지의 합은?</title>
</head>
<body>
    <%
    int total = 0;
    for(int i=1;i<=100;i++){
        total+=i;
    }
    %>
    1부터 100까지의 합은?! <%= total %>
</body>
</html>