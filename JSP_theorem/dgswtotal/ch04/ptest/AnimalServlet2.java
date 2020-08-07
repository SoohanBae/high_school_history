import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class AnimalServlet2 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");

        String food = request.getParameter("food");
        HttpSession session = request.getSession();
        session.setAttribute("food", food);

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD><TITLE>성격 테스트</TITLE><HEAD>");
        out.println("<BODY>");
        out.println("<H3>좋아하는 동물은?</H3>");
        out.println("<form action=ResultServlet2>");
        out.println("<input type = TEXTFIELD name=animal>");
        out.println("<input type = SUBMIT value=\"확인\">");
        out.println("</form>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
