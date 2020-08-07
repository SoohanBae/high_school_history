<%@pagecontentType="text/html; charset=utf-8"%>
<%@page import="java.io.*"%>
<html>
    <head><title>파일 읽기</title></head>
    <body>
        <%
            String filePath = request.getParameter("m_name");
        %>
        <h2><%= filePath %></h2>
        <%
            BufferedReader reader = null;
            try{
                String str = application.getRealPath("/WEB-INF/languge/" + filePath);  
                reader = new BufferedReader(new FileReader(str));
                while(true) {
                    String st = reader.readLine();
                    if(st==null)
                        break;
                    out.println(st+"<br>");
                }
            }
            catch(FileNotFoundException fnfe){
                out.println("파일이 존재하지 않습니다.");
            } catch(IOException ioe) {
                out.println("파일을 읽을 수 없습니다.");
            } finally {
                try{
                    reader.close();
                }catch(Exception e) {
                }
            }
        %>
    </body>
</html>