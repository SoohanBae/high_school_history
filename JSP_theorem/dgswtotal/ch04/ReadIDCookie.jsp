<%@page contentType="text/html; charset=euc-kr"%>
<% Cookie[] cookies = request.getCookies(); %>
<HTML>
    <HEAD><TITLE>쿠키 데이터 읽기</TITLE></HEAD>
    <BODY>
        LOGIN_ID: <%= getCookieValue(cookies, "LOGIN_ID") %>
    </BODY>
</HTML>
<%!
    private String getCookieValue(Cookie[] cookies, String name) {
        String value = null;
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name))
                return cookie.getValue();
        }
        return null;
    }
%>
