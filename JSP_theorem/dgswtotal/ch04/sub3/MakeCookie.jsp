<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.io.*"%>
<%@page session="false"%>
<%@page import="java.net.URLEncoder"%>


<html>
    <HEAD><TITLE>쿠키 보여주기</TITLE></HEAD>
    <BODY>
    <%
        String cookieName = request.getParameter("COOKIE_NAME");
        String cookieValue = request.getParameter("COOKIE_VALUE");
        cookieName = URLEncoder.encode(cookieName, "utf-8");
        response.addCookie(new Cookie(cookieName,cookieValue));
        response.sendRedirect("DisplayCookie.jsp");
    %>
    </body>
</html>