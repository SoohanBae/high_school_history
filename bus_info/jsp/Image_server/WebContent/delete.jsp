<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*,java.io.*" %>

<%
    request.setCharacterEncoding("EUC-KR");
    String name = request.getParameter("name");

    File file = new File("G:\\bus_info\\jsp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Image_server\\img\\"+name);
    file.delete();
    
    response.sendRedirect(application.getContextPath() + "/upload.jsp?");
%>