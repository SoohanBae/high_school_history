<%@page contentType="text/html; charset=utf-8"%>
<%
    request.setCharacterEncoding("utf-8");
    String address = request.getParameter("ADDRESS");
    String callnumber = request.getParameter("CALLNUMBER");
    String email = request.getParameter("EMAIL");
    session.setAttribute("ADDRESS", address);
    session.setAttribute("CALLNUMBER", callnumber);
    session.setAttribute("EMAIL", email);
%>

<html>
    <head>
        <title>약관 동의</title>
    </head>
    <body>
        <h2>약관</h2>
        ---------------------------------------------------------<br>
        1. 회원 정보는 웹 사이트의 운영을 위해서만 사용됩니다.<br>
        2. 웹 사이트의 정상 운영을 방해하는 회원은 탈퇴 처리합니다.<br>
        ---------------------------------------------------------<br>
        <form action=Subscribe.jsp method="post">
            위의 약관에 동의하십니까?
            <input type="radio" name="AGREE" value="YES">동의함
            <input type="radio" name="AGREE" value="NO">동의하지 않음<br><br>
            <input type="submit" value="확인">
        </form>

    </body>
</html>