import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ResultServlet2 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");


        HttpSession session = request.getSession();
        
        int timeout = session.getMaxInactiveInterval();
        session.setMaxInactiveInterval(300);

        String food = (String) session.getAttribute("food");
        String animal = request.getParameter("animal");

        int timeout2 = session.getMaxInactiveInterval();

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD><TITLE>성격 테스트</TITLE><HEAD>");
        out.println("<BODY>");
        out.printf("당신은 %s와 %s를 좋아하는 성격입니다." , food, animal);
        out.printf("세션 timeout은 %d => %d로 변경됨", timeout, timeout2); 
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
