<%@page contentType="text/html; charset=euc-kr"%>
<%
    Cookie cookie = new Cookie("LOGIN_ID", "lion2010");
    cookie.setDomain(".hanb.co.kr");
    response.addCookie(cookie);
%>
<HTML>
    <HEAD><TITLE>쿠키 데이터 저장하기</TITLE></HEAD>
    <BODY>
        LOGIN_ID 쿠키가 저장되었습니다.
    </BODY>
</HTML>
