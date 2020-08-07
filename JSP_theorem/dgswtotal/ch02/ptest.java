import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ptest extends HttpServlet{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=\"UTF-8\";pageEncoding=\"UTF-8\"");

    String name = request.getParameter("name");
    String color = request.getParameter("color");
    String animal = request.getParameter("animal");
    String food[] = request.getParameterValues("food");

    PrintWriter out = response.getWriter();
    out.printf("<html>");
    out.printf("<head><title>성격 테스트</title></head>");
    out.printf("<body>");
    out.println("<h2>성격 테스트</h2>");
    out.printf("%s님의 성격 테스트 결과<br><br>",name);
    out.printf("%s 색을 좋아하는 당신은 %s, 그리고 ",color,animal);
    if(food == null){
      out.printf("좋아하는 음식이 없는 성격입니다.");
    }else{
      for(int i = 0; i < food.length; i++){
        if(i>0)
          out.printf("과 ");

        out.printf("%s",food[i]);
      }
      out.printf("을 좋아하는 성격입니다.");
    }
    out.printf("</body>");
    out.printf("</html>");

  }
}
