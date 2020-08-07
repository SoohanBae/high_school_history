import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ex2_1 extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int total = 0;
		int n = 100;
		total = n*(n+1)/2;

		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><META http-equiv = \"content-type\" content=\"text/html\"; charset=\"utf-8\"><TITLE>1부터 100의 합</TITLE><HEAD>");
		out.println("<BODY>");
		out.printf("0~100합 %d",total);
		out.println("</BODY>");
		out.println("</HTML>");
	}
}
