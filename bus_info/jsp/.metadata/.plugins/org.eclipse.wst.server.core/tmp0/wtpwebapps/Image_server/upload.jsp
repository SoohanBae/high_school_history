<%@ page language="java" contentType="text/html; charset=EUC-KR"
pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, java.io.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Insert title here</title>
</head>





<form method="post" enctype="multipart/form-data" action="imgup.jsp">
<input type="file" name="filename1" size=40>
<input type="submit" value="파일 추가"><br><br>
</form>

<body>
<%
String path = "G:\\bus_info\\jsp\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Image_server\\img";
File dirFile = new File(path);
File []fileList=dirFile.listFiles();

for(File tempFile : fileList){
	if(tempFile.isFile()){
		String tempPath = tempFile.getParent();
		
		String tempFileName=tempFile.getName();
	    System.out.println("Path="+tempPath);
	    System.out.println("FileName="+tempFileName);
	    out.print("<form method=\"post\" action=\"delete.jsp\">");
	    out.print("<img src=\"http://localhost:8080/Image_server/img/"+tempFileName+"\" width=\"210\" height=\"297\"/>");
	    out.print("<input type=\"hidden\" name=\"name\" value = \""+tempFileName+"\">");
	   
	    out.print("<input type=\"submit\" value=\"삭제\"><br><br></form>");
	}
}
%>

</body>
</html>



