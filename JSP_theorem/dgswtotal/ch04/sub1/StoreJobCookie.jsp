<%@page contentType="text/html; charset=euc-kr"%>
<%
    Cookie cookie = new Cookie("JOB", "programmer");
    cookie.setPath("/brain04/sub1/");
    response.addCookie(cookie);
%>
<HTML>
    <HEAD><TITLE>쿠키 데이터 저장하기</TITLE></HEAD>
    <BODY>
        JOB 쿠키가 저장되었습니다. <BR><BR>
    </BODY>
</HTML>
