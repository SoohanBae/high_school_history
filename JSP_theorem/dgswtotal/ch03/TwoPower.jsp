<%@pagecontentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>2의 거듭제곱</title>
</head>
<body>
    2 ^ 1 = <%= power(2, 1) %> <br>
    2 ^ 2 = <%= power(2, 2) %> <br>
    2 ^ 3 = <%= power(2, 3) %> <br>
    2 ^ 4 = <%= power(2, 4) %> <br>
</body>
</html>
<%!
    private int power(int base, int exponent) {
        int result = 1;
        for(int cnt=0;cnt<exponent;cnt++){
            result *= base;
        }
        return result;
    }

%>