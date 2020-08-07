<%@pagecontentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>1부터 100까지의 합은?</title>
</head>
<body>
    <%
    int total = 0;
    for(int cnt=1;cnt<=100;cnt++){
        total+=cnt;
    }
    %>
    1부터 100까지의 합 = <%= total %> <br>

    <%
    for(int cnt=101;cnt<=200;cnt++){
        total+=cnt;
    }   
    %>
    1부터 200까지의 합 = <%= total %> <br>
    
</body>
</html>