import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class HundredServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=utf-8");
      int total = 0;
      for(int cnt=1;cnt<=200;cnt++){
          total+=cnt;
      }
    RequestDispatcher dispatcher = request.getRequestDispatcher("Hundred2.jsp");
	request.setAttribute("result",total);
	dispatcher.forward(request, response);

  }
} 