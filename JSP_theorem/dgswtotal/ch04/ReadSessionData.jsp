<%@page contentType="text/html; charset=euc-kr"%>
<HTML>
    <HEAD><TITLE>세션 데이터를 읽는 JSP 페이지</TITLE></HEAD>
    <BODY>
        이름: <%= session.getAttribute("NAME") %> <BR>
        나이: <%= session.getAttribute("AGE") %> <BR>
        성별: <%= session.getAttribute("GENDER") %> 
    </BODY>
</HTML>
