<%@page contentType="text/html; charset=utf-8"%>

<%!
    public boolean loginCheck(String id, String password){
        if(id.equals("a") && password.equals("b"))
            return true;
        else
            return false;
    }
%>

<%
    String id = request.getParameter("ID");
    String password = request.getParameter("PASSWORD");

    if(loginCheck(id,password)){
        session.setAttribute("ID",id);
    }
        response.sendRedirect("LoginResult.jsp");
%>

<html>
    <HEAD><TITLE>쿠키 보여주기</TITLE></HEAD>
    <BODY>
        
    </body>
</html>