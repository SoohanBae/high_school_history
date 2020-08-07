import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class BBSInput extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");


	String name = request.getParameter("NAME");
	String title = request.getParameter("TITLE");
	String content = request.getParameter("content");


	PrintWriter out = response.getWriter();
	out.println("<HTML>");
	out.println("<HEAD><TITLE>출력결과</TITLE></HEAD>");
	out.println("<BODY>");
	out.printf("이름:"+name+" <BR>");
	out.printf("제목:%s <BR>",title);
	out.printf("--------------<BR>");
	out.printf("<PRE>%s</PRE>",content);
	out.printf("--------<BR>");
	out.println("</BODY>");
	out.println("</HTML>");

}
}
