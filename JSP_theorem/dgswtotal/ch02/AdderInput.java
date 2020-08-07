import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class AdderInput extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	String str1 = request.getParameter("NUM1");
	String str2 = request.getParameter("NUM2");
	int num1 = Integer.parseInt(str1);
	int num2 = Integer.parseInt(str2);
	

	int sums = num1+num2;

	response.setContentType("text/html;charset=euc-kr");
	PrintWriter out = response.getWriter();
	out.println("<HTML>");
	out.println("<HEAD><TITLE>두 값의 합</TITLE></HEAD>");
	out.println("<BODY>");
	out.printf("%d + %d = %d",num1, num2, sums);
	out.println("</BODY>");
	out.println("</HTML>");

} 
}