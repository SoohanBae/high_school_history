# jsp

## 스크립트 요소?
JSP내에 자바 코드를 삽입하기 위해 사용
1. 스크립틀릿  
`<% 순수 자바 코드 기술%> //서버딴에서 처리함` 
2. 선언문  
`<%! 전역변수, 메소드 %> //선언하면서 제일 먼저 실행됨` 
3. 표현식
`<%= 클라이언트에 출력할 내용%> //;사용을 하면 안됨, 코드의 간결성을 도와줌 ` 

## 익스프레션 언어 사용하기
servlet에서 값을 넘기면 익스프레션 언어로 쉽게 받는다  
※html  
```
<body>
1부터 200까지의 합은?! ${result}
</body>
```
※java  
```
RequestDispatcher dispatcher = request.getRequestDispatcher("Hundred2.jsp");
request.setAttribute("result",total);
dispatcher.forward(request, response);
```


## 지시자 문법  
jsp 컨테이너에 해당 페이지의 특수한 처리 정보를 넣고자 할 때 사용한다. 예를 들어 사용할 스크립팅언어를 지정하거나, 다른 페이지의 컨텐트를 삽입하고, 커스텀 태그 라이브러리를 지정할 때 사용된다.  
1. `<%@page  %> ContetType은 문자집합, MIME타입  import는 클래스/패키지이름 추가 ` 
2. `<%@include  %> //다른 파일들을 포함시킬 떄 사용`  
3. `<%@taglib> // 새로운 태그를 추가시킬때 사용`


※`<c:forEach var="num" items="${ARR}"> // java의 foreach랑 같다` 

## 주석들
`//` `/* */` `<%-- --%>` `<!-- -->`

## 내장변수
1. `request.getParameter("파일이름") // 파라미터로 온 값을 받는다`
2. `out.print("출력하고 싶은것"); // java코드에서 출력할 때 사용`
3. `response.sendRedirect("경로"); // 새로운 페이지로`
4. `application.getContextPath(); //usl 경로명`
5. `application.getRealpath("파일경로"); //실제 경로를 보여준다`

## jsp 파일입출력
※ 읽기
```
 <%
    BufferedReader reader = null; //선언
    try{
        String filePath = application.getRealPath("input.txt"); //실제 경로 얻기
        reader = new BufferedReader(new FileReader(filePath));  //파일 열기
        while(true) {
            String str = reader.readLine();  //한줄읽기
            if(str==null)
                break;
            out.println(str+"<br>"); //웹브라우저에 출력
        }
    } catch(FileNotFoundException fnfe){
        out.println("파일이 존재하지 않습니다.");
    } catch(IOException ioe) {
        out.println("파일을 읽을 수 없습니다.");
    } finally {
        try{
            reader.close();  //파일을 닫는다
        }catch(Exception e) {
        }
    }
        
%>
```
※ 출력
```
<%
    request.setCharacterEncoding("utf-8");
    String name = request.getParameter("name");
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    Date date = new Date();
    Long time = date.getTime();  
    String filename = time + ".txt"; //시간을 기준으로 파일저장
    PrintWriter writer = null;

    try {
        String filePath = application.getRealPath("/WEB-INF/bbs/" + filename);
        writer = new PrintWriter(filePath); //쓰는것을 연다
        writer.printf("제목 : %s %n", title);
        writer.printf("글쓴이: %s %n", name); //쓴다
        writer.println(content);
        out.println("저장되었습니다.");
    } catch (IOException ioe) {
        out.println("파일에 데이터를 쓸 수 없습니다.");
    } finally {
        try {
            writer.close(); //닫는다
        } catch (Exception e) {
        }
    }
%>
```

## 예제들
- [1부터 100까지의 합](http://localhost:8080/dgswtotal/ch02/Hundred.jsp)
- [1부터 200까지의 합 - 익스프레션 언어](http://localhost:8080/dgswtotal/ch02/HundredServlet)
- [1부터 200까지의 합 - 스크립틀릿](http://localhost:8080/dgswtotal/ch02/TwoHundred.jsp)
- [제곱하기 - 선언부](http://localhost:8080/dgswtotal/ch02/TwoPoser.jsp)
- [시간확인 - 지시자](./DateTime.jsp)
- [메뉴확인 - 지시자](./Menu.jsp)
- [랜덤숫자 - 지시자](./Winners.jsp)
- [주석처리 - 지시자](./TenMuliply.jsp)
- [너의이름은 - request](./YourName.jsp)
- [숫자출력 - 익스프레션 언어](./oneToTen1.jsp)
- [숫자출력 - out.println()](./oneToTen2.jsp)
- [페이지 연결 - 내장변수](./Move.jsp)
- [경로얻기 - application](.AppTest.jsp)
- [파일읽기](./FileReader.jsp)
- [인기 코드 목록 - 파일입출력](./langugeReader.jsp)
- [게시판 글쓰기 - 파일출력](./BBSInput.html)
- [게시판 글쓰기2 - 위 예제 수정](./BBSInput2.html)
- [사칙 연산 수행 - forward](./FourRulesInput.html)
- [중국음식 + 시간](./ChineseMenu.jsp)
- [점점 더 크게 인사하기](./Greetings.jsp)


