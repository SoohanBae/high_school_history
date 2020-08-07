<%@page contentType="text/html; charset=utf-8"%>
<%
    request.setCharacterEncoding("utf-8");
    String id = request.getParameter("ID");
    String password = request.getParameter("PASSWORD");
    String name = request.getParameter("NAME");
    session.setAttribute("ID", id);
    session.setAttribute("PASSWORD", password);
    session.setAttribute("NAME", name);
%>

<html>
  <head>
    <title>회원 가입2</title>
  </head>
  <body>
    <h3>추가정보를 입력해 주세요</h3>
    <form action="Agreement.jsp" method = "POST">
        주소 : <input type="text" name="ADDRESS"><br>
        전화번호 : <input type="text" name="CALLNUMBER"><br>
        이메일 : <input type = "text" name = "EMAIL"><br><br>
        <input type = "submit" value = "확인"><br>
    </form>
  </body>
</html>