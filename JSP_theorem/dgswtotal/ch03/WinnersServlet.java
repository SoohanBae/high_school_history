import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.Random;


public class WinnersServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        
        int arr[] = new int[5];
        for(int cnt = 0 ; cnt < arr.length; cnt++){
            arr[cnt] = new Random().nextInt(10);
        }
            
        request.setAttribute("ARR", arr);
        RequestDispatcher rd = request.getRequestDispatcher("Winners.jsp");
        rd.forward(request, response);
	}
}