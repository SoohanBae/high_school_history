# Servlet 기초

## 개요
Servlet을 사용하기 위한 기본틀이 있다.
~~~
import javax.servlet.http.*;
import javax.servlet.*;
import java,io,*;

public class ex1 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response throws ServletException, IOException {

	response.setContentType("text/html; charset=문자형");	
	
	PrintWriter out = response.getWriter();
	out.printf("html코드");
    }
}
~~~

컴파일을 한 뒤 `WEB-INF\Classes` 하위폴더에 클래스 파일을 넣는다.  
또한 `WEB-INF`에 아래와 같이 등록한다
```
<servlet>
	<servlet-name>ex2_1-servlet</servlet-name>
	<servlet-class>ex2_1</servlet-class>
</servlet>
<servlet-mapping>
	<servlet-name>ex2_1-servlet</servlet-name>
	<url-pattern>/ch02/ex2_1</url-pattern>
</servlet-mapping>
```
  
<br>
<br>

## 예제 1 - 1부터 100의 합 출력하는 페이지 만들기
```
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
```
<!--코드 프레임 넣기--> 


<br>


## 예제 2 - 2또는 3의 배수가 아닌 수의 총 합을 구하는 페이지
```
for (int i = 1; i <= n; i++) {
	if(i%2 != 0 && i%3 != 0 ){
		total+=i;
	}
}
```

<!-- 코드 프레임 넣기-->

<br>

## 예제 3 - 웹 페이지로 데이터를 받고 계산하기
※ html 
```
<HTML>
	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=utf-8">
		<TITLE>덧셈 프로그램 - 입력 화면</TITLE>
	</HEAD>
	<BODY>
		<form action="http://localhost:8080/dgswtotal/ch02/AdderInput" method="get">
		첫번째 수: <INPUT TYPE=TEXT NAME=NUM1><BR>
		두번째 수: <INPUT TYPE=TEXT NAME=NUM2><BR>
		<INPUT TYPE=SUBMIT VALUE='더하기'>
		</FORM>
	</BODY>
</HTML>

```
※ java
```
String str1 = request.getParameter("NUM1");
String str2 = request.getParameter("NUM2");
int num1 = Integer.parseInt(str1);
int num2 = Integer.parseInt(str2);
```

<!--코드 프레임 넣기--> 


<br>

## 예제 4 - post 메서드를 이용한 데이터 전송
※ 위에서 method 방식만 다를뿐임
`<form action="http://localhost:8080/dgswtotal/ch02/AdderInput" method="post">`


<!--코드 프레임 넣기--> 


<br>

## 예제 5,6 - doGet과 doPost
※ get방식과 post방식을 쓰는 곳을 구분해야 한다.  
※ get방식은 주소에 값이 들어가기 때문에 쇼핑물 같은데서 사용  
※ post방식은 주소에 값이 들어가지 않고 숨겨서 값을 보내기 때문에 회원가입에서 사용  

[새로운 탭-doGet방식](http://localhost:8080/dgswtotal/ch02/PinfoInput.html)  
[새로운 탭-doPost방식](http://localhost:8080/dgswtotal/ch02/PinfoInput2.html) 

<!--코드 프레임 넣기--> 


<br>

## 예제 7 - 피보나치 수열


<!--코드 프레임 넣기--> 


<br>

## 예제 8 - 성격테스트

