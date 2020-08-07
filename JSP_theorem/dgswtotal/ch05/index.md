# 익셉션 처리
## 올바른 익셉션 처리
그 페이지에서 에러를 처리하지 말고 다른 에러페이지를 호출하는 것이 바람직 하다

## 사용방법
※에러가 날 수 있는 파일
`<@page contentType="text/html; charset=utf-8 errorPage="에러페이지 파일"%>"`
※에러 페이지 파일
`<@page contentType="text/html; charset=utf-8 isErrorPage="true"%>`
`<% response.setStatues(200); //jsp 내장 errorPage를 출력하지 않기 위해%>`

## 예제
- [잘못된 예제1](./AdderServlet)
- [잘못된 예제2](./Adder1.jsp)
- [올바른 예제1](./Adder2.jsp)
- [errorPage 사용하기](./NewAdder.jsp)
- [서블릿에서 error 사용](./NewAdderServlet)