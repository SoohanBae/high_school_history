import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class fibo extends HttpServlet{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


    response.setContentType("text/html;charset=\"UTF-8\";pageEncoding=\"UTF-8\"");

    PrintWriter out = response.getWriter();
    out.println("<HTML>");
    out.println("<HEAD><TITLE>피보나치 수열의 합</TITLE><HEAD>");
    out.println("<BODY><H2>");
    int a=1;
    int b=0;
    for (int i = a+b; i < 100; i = a + b) {
      out.printf("%d ",i);
      a=b;
      b=i;
    }
    out.println("</H2></BODY>");
    out.println("</HTML>");
  }
}
