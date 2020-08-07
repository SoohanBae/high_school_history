# 쿠키
## 개념
사용자의 컴퓨터에 저장되는 작은 기록 정보 파일  
## 사용법
- `response.addCookie(new Cookie("이름",값")) // 쿠키를 저장한다`
- `request.getCookies() //쿠키를 다 가지고 온다`
- `response.addCookie(new Cookie("이미있는 이름",값")) // 쿠키를 수정한다`
- `cookie.setMaxAge(0) //쿠키의 만료시간을 0으로 하여 삭제한다`
- `cookie.setPath("경로") //특정 경로를 갖는 URL로만 전송되도록 만든다`

## 예제
- [쿠키 저장](./StoreCookies.jsp)
- [쿠키 읽기](./ReadCookies.jsp)
- [쿠키 수정](./ModifyCookie.jsp)
- [쿠키 삭제](./DeleteCookie.jsp)
- [쿠키 특정 경로](./sub1/StoreCookies.jsp)
- [쿠키 특정 경로 읽기](./sub1/StoreCookies.jsp)
- [쿠키 특정 경로 읽기 - 다른경로](./sub2/StoreCookies.jsp)
  
<br>

# 세션  

## 개념
세션(session)은 웹 서버 쪽의 웹 컨테이너에 상태를 유지하기 위한 정보를 저장한다.  
세션은 사용자가 웹 브라우저를 종료했을 때, 서버가 재시작 되었을 때, 종료 코드가 실행될 때 종료된다.

## 사용법
- `HttpSession session = request.getSession() //세션시작`
- `session.setAttribute("이름", 값) //세션에 값 쓰기`
- `session.getAttribute("이름") //세션 값 읽기`
- `session.setAttribute("이미있는 이름") //세션 값 수정`
- `session.setMaxInactiveinterval(시간) //세션 종료 시간 설정`
- `request.getSession() //이미 시작된 세션을 계속한다`
- `session.invalidate() //세션 종료하기`  

## 예제
- [좋아하는 음식 - set,get,종료](./ptest/Food.html)
- [위 예제 + 시간만료](./ptest/Food2.html)
- [회원가입](./subscribe/PersonalInfo.html)
- [어벤져스](./sub3/InputCookie.html)
- [로그인 폼](./sub3/LoginForm.html)
