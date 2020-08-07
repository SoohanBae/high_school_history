<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.io.*"%>
<%@page session="false"%>
<%@page import="java.net.URLDecoder"%>


<html>
    <HEAD><TITLE>쿠키 보여주기</TITLE></HEAD>
    <BODY>
    어벤져스(히어로) : 전투력 <br>
    <hr>
    <%
        Cookie cookies[] = request.getCookies();
        for(Cookie cookie : cookies){
            String name = URLDecoder.decode(cookie.getName(), "utf-8");
            String value = URLDecoder.decode(cookie.getName(), "utf-8");
            out.print(name+" : "+value+"<br>");
        }
    %>
    <a href="InputCookie.html">어벤져스 등록화면으로 </a>
    </body>
</html>