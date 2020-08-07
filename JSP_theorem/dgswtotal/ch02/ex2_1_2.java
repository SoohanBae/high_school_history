import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ex2_1_2 extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int total = 0;
		int n = 200;
		for (int i = 1; i <= n; i++) {
				if(i%2 != 0 && i%3 != 0 ){
					total+=i;
				}
		}
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>1.. 200</TITLE><HEAD>");
		out.println("<BODY>");
		out.printf("1.. 200 not 2,3 baesoo = %d",total);
		out.println("</BODY>");
		out.println("</HTML>");
	}
}
