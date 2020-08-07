<%@pagecontentType="text/html; charset=utf-8"%>
<%@page import="java.io.*"%>
<html>
    <head><title>노래 정보 읽어오기</title></head>
    <body>
        <h2>인기 노래 목록</h2>
        <%
        File music = new File(application.getRealPath("/WEB-INF/languge"));
        String[] lists = music.list();
        for(String m_name : lists) {
            out.print("<a href ='langugeReader.jsp?m_name=" + m_name + "'>"+ m_name + "<br>");
        }
        %>
    </body>
</html>