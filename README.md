# 톰캣 구현하기
톰캣(WAS)

1. 서블릿 객체 관리(생성, 초기화, 호출, 종료)
2. HttpServletRequest, HttpServletResponse 생성

톰캣 컴포넌트 구성

1. Coyote(HTTP Component)
    1. 톰캣에 TCP를 통한 프로토콜 지원
    2. Coyote는 HTTP 1.1 및 2 프로토콜을 웹 서버로 지원하는 Tomcat용 커넥터 구성 요소입니다. 이를 통해 명목상 Java 서블릿 또는 JSP 컨테이너인 Catalina가 로컬 파일을 HTTP 문서로 제공하는 일반 웹 서버로도 작동할 수 있습니다. Coyote는 특정 포트에서 서버로 들어오는 연결을 수신하고 Tomcat 엔진에 요청을 전달하여 요청을 처리하고 요청하는 클라이언트에 응답을 보냅니다.
    3. Coyote는 HTTP 1.1 및 2 프로토콜을 웹 서버로 지원하는 Tomcat용 커넥터 구성 요소입니다.
2. Catalina(Servlet Container)
    1. Java Servlet을 호스팅하는 환경
    
3. Jasper(JSP Engine)
    1. 실제 JSP 페이지의 요청을 처리하는 Servlet
       
동작 과정

1. HTTP 요청을 Coyote 에서 받아서 Catalina로 전달한다.
2. Catalina에서 전달받은 HTTP 요청을 처리할 웹 어플리케이션을 찾는다.
3. 요청된 Servlet을 통해 생성된 jsp 파일들이 호출될 때, Jasper이 Validation Check / Complie 등을 수행한다.
4. Coyote가 HTTP요청을 받으면 Catalina 서블릿 컨테이너에서 요청중에서 java웹 어플리케이션을 해석하는데, 그중에 jsp에 관한 요청 일때 Jasper가 담당해서 처리.
<img width="670" alt="스크린샷 2022-09-05 오후 3 00 00" src="https://github.com/Youngyoon-1/jwp-dashboard-http/assets/76875654/a8bdc9e2-74c2-4baf-af1a-4b6ac5c16efd">
