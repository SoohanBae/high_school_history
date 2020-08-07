<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.io.*"%>
<%
    String result = request.getParameter("RESULT");

%>
<html>
    <HEAD><TITLE>회원 가입</TITLE></HEAD>
    <BODY><H3>회원 가입 결과</H3>
    <%
        if(result.equals("SUCCESS"))
            out.print("가입 되었습니다.");
        else
            out.print("가입되지 않았습니다.");
    %>
</html>