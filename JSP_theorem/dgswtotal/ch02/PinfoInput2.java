import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class PinfoInput2 extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");


	String name = request.getParameter("name");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
  String gender = request.getParameter("gender");
  if (gender.equals("male")){
    gender = "남";
  }else{
    gender = "여";
  }

  String inotice = request.getParameter("inotice");
  String cnotice = request.getParameter("cnotice");
  String dnotice = request.getParameter("dnotice");
  String job = request.getParameter("jobs");


	PrintWriter out = response.getWriter();
	out.println("<HTML>");
	out.println("<HEAD><TITLE>출력결과</TITLE></HEAD>");
	out.println("<BODY>");
	out.println("<h2>개인 정보 입력</h2>");
  out.printf("이름 : %s <br>",name);
  out.printf("아이디 : %s <br>",id);
  out.printf("패스워드 : %s <br>",password);
  out.printf("성별 : %s <br>",gender);
  out.printf("공지 메일 : %s <br>",noticeToHangul(inotice));
  out.printf("광고 메일 : %s <br>",noticeToHangul(cnotice));
  out.printf("배송 확인 메일 : %s <br>",noticeToHangul(dnotice));
  out.printf("직업 : %s <br>",job);
	out.println("</BODY>");
	out.println("</HTML>");

  }

  private String noticeToHangul(String notice){
    if(notice == null) return "받지 않음";
    else if(notice.equals("on")) return "받음";
    else return notice;
  }
}
