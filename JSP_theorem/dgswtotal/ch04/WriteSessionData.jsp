<%@page contentType="text/html; charset=euc-kr"%>
<% 
    session.setAttribute("NAME", "김지영"); 
    session.setAttribute("AGE", new Integer(21)); 
    session.setAttribute("GENDER", "여"); 
%>
<HTML>
    <HEAD><TITLE>세션 데이터를 저장하는 JSP 페이지</TITLE></HEAD>
    <BODY>
        세션 데이터가 저장되었습니다. <BR><BR>
        <A href=<%= response.encodeURL("ReadSessionData.jsp") %>>세션 데이터 읽기</A>
    </BODY>
</HTML>
