# 스프링 web MVC
[강의링크](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-1/dashboard)

# 1. 웹 애플리케이션 이해
## 웹 서버, 웹 애플리케이션 서버
### 모든것이 HTTP
- HTTP 메시지에 모든 것을 전송한다.

### 웹 서버(Web Server)
- HTTP 기반으로 동작
- 정적 리소스 제공, 기타 부가기능
- Ex. NGINX, APACHE

### 웹 애플리케이션 서버(WAS- Web Application Server)
- HTTP 기반으로 동작
- 웹 서버 기능 포함 + (정적 리소스 제공 가능)
- 프로그램 코드를 실행해서 애플리케이션 로직 수행
    - 동적 HTML, HTTP API(JSON)
    - 서블릿, JSP, 스프링 MVC
- Ex. 톰캣(Tomcat), Jetty, Undertow

### 웹 서버 VS 웹 애플리케이션 서버
- 웹 서버는 정적 이로스(파일), WAS 애플리케이션 로직
- 사실은 둘의 용어도 경계도 모호함
    - 웹 서버도 프로그램을 실행하는 기능을 포함하기도 함
    - 웹 애플리케이션 서버도 웹 서버의 기능을 제공함
- 자바는 서블릿 컨테이너 기능을 제공하면 WAS
    - 서블릿 없이 자바코드를 실행하는 서버 프레임워크도 있음.
- WAS는 애플리케이션 코드를 실행하는데 더 특화

### 웹 시스템 구성 - WAS, DB
- [WAS] - [DB]
- WAS, DB 만으로 시스템 구성 가능
- WAS는 정적리소스, 애플리케이션 로직 모두 제공 가능
- BUT! WAS가 너무 많은 역할을 담당
- -> [웹 서버] - [WAS] - [DB]
- 정적 리소스는 웹 서버가 처리
- 업무 분담.
- 효율적인 리소스 관리
    - 정적 리소스가 많이 사용되면 Web server 증설
    - 애플리케이션 로직이 많아지면 WAS 증설
- 정적 리소스만 제공하는 웹 서버는 잘 죽지 않음
- 애플리케이션 로직이 동작하는 WAS 서버는 잘 죽음
- WAS, DB 장애시 웹 서버가 오류화면 제공 가능

## 서블릿
- HTTP 요청정보를 편리하게 사용할 수 있는 HttpServletRequest
- HTTP 응답정보를 편리하게 제공할 수 있는 HttpServletResponse
- 개발자는 HTTP 스펙을 매우 편리하게 사용할 수 있다.
### HTTP 요청, 응답 흐름
- HTTP 요청시
    - WAS는 Request, Response 객체를 새로 만들어서 서블릿 객체 호출
    - 개발자는 Request 객체에서 HTTP 요청 정보를 편리하게 꺼내서 사용
    - 개발자는 Response 객체에 HTTP 응답 정보를 편리하게 입력
    - WAS는 Response 객체에 담겨있는 내용으로 HTTP 응답 정보를 생성
### 서블릿 컨테이너
- 톰캣처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너 라고 함
- 서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기 관리
- 서블릿 객체는 싱글톤으로 관리
    - 고객의 요청이 올 때 마다 계속 객체를 생성하는 건 비효율
    - 최초 로딩 시점에 서블릿 객체를 미리 만들어두고 재활용
    - 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근
    - **공유 변수 사용 주의**
    - 서블릿 컨테이너 종료시 함께 종료
- JSP도 서블릿으로 변환되어서 사용
- 동시 요청을 위한 멀티 쓰레드 처리 지원
## 동시요청 - 멀티 쓰레드
### 쓰레드
- 애플리케이션 코드를 하나하나 순차적으로 실행하는 것
- 자바 메인 메서드를 처음 실행하면 MAIN이라는 이름의 쓰레드가 실행됨
- 쓰레드가 없다면 자바 애플리케이션 실행이 불가능
- 쓰레드는 한번에 하나의 코드 라인만 수행
- 동시처리가 필요하면 쓰레드를 추가로 생성
### 요청마다 쓰레드 생성?
- 장점
    - 동시 요청을 처리할 수 있다.
    - 리소스가 허용될 때 까지 처리가능
    - 하나의 쓰레드가 지연 되어도, 나머지 쓰레드는 정상 동작
- 단점
    - 쓰레드 생성 비용이 매우 비싸다
    - 쓰레드는 컨텍스트 스위칭 비용이 발생
    - 쓰레드 생성에 제한이 없다. -> CPU, 메모리 임계점을 넘어서 서버가 죽을 수 있다.
### 쓰레드 풀
- 요청마다 쓰레드 생성의 단점 보완
- 특징
    - 필요한 쓰레드를 쓰레드 풀에 보관하고 관리한다.
    - 톰캣은 최대 200개 기본 설정(변경가능)
- 사용
    - 쓰레드가 필요하면, 이미 생성되어있는 쓰레드를 쓰레드 풀에서 꺼내서 사용
    - 사용 종료하면 쓰레드 풀에 반납
    - 기다리는 요청은 거절하거나 특정 숫자만큼만 대기하도록 설정 가능
- 쓰레드가 미리 생성되어 있으므로, 쓰레드를 생성/종료하는 비용이 절약되고 응답시간이 빠르다.
### 실무팁!
- was의 주요 튜닝포인트는 최대 쓰레드 수이다.
### WAS의 멀티쓰레드 지원
- 멀티 쓰레드에 대한 부분은 WAS가 처리
- **개발자는 멀티 쓰레드 관련 코드를 신경쓰지 않아도됨**
## HTML, HTTP API, CSR, SSR
### SSR - 서버사이드 렌더링
- 서버에서 최종 HTML을 생성해서 클라이언트에 전달
- 주로 정적인 환경에 사용
### CSR - 클라이언트 사이드 렌더링
- HTML 결과를 자바스크립트를 사용해 웹 브라우저에서 동적으로 생성해서 적용
- 주로 동적인 화면에 사용, 웹 환경을 마치 앱 처럼 필요한 부분부분 변경할 수 있음
## 자바 백엔드 웹 기술 역사
### 서블릿 - 1997
- HTML 생성이 러엽다.
### JSP - 1999
- HTML 생성은 편리하지만, 비즈니스 로직까지 너무 많은 역할 담당
### 서블릿, JSP 조합 MVC 패턴 사용
- 모델, 뷰, 컨트롤러로 역할을 나누어 개발
### MVC 프레임워크 춘추 전국 시대 - 2000년초~2010년초
### 애노테이션 기반의 스프링 MVC 등장
- MVC 프레임워크의 춘추 전국 시대 마무리
### 스프링 부트의 등장
- 스프링부트는 서버를 내장
- 과거에는 서버에 WAS를 직접 설치하고, 소스는 War파일을 만들어서 설치한 WAS에 배포
- 스프링 부트는 빌드결과(Jar)에 WAS 서버 포함 -> 빌드 배포 단순화
### 스프링 웹 기술의 분화
- Web Servlet - Spring MVC
- Web Reactive - Spring WebFlux
### 최신기술 - 스프링 웹 플럭스
- 특징
    - 비동기 넌 블러킹 처리
    - 최소 쓰레드로 최대 성능
    - 함수형 스타일로 개발 -> 동시처리 코드 효율화
    - 서블릿 기술 사용x (netty)
- but
    - 기술적 난이도 매우 높음
    - 아직은 RDB지원 부족
    - 일반 MVC의 쓰레드 모델도 충분히 빠르다
    - 실무에서 아직 많이 사용하지는 않음(전체 1% 이하)

# 2. 서블릿
## 프로젝트 생성
## Hello 서블릿
## HttpServletRequest - 개요
- HTTP 요청 메시지를 개발자가 편리하게 사용할 수 있도록 서블릿이 요청 메시지를 파싱한다.
- 그리고 그 결과를 `HttpServletRequest` 객체에 담아서 제공한다.
- **임시 저장소 기능**: 해당 HTTP 요청이 시작부터 끝날 때까지 유지되는 임시 저장소 기능
- **세션 관리 기능**
## HTTP ServletRequest - 기본사용법
## HTTP 요청 데이터 - 개요
- 주로 3가지 방법을 사용한다.
    - GET - 쿼리 파라미터
        - 메세지 바디 없이, 쿼리 파라미터에 데이터를 포함해서 전달.
        - ex. 검색, 필터, 페이징등에서 많이 사용한다.
    - POST - HTML Form
        - 메세지 바디에 쿼리 파라미터 형식으로 전달
        - ex. 회원가입, 상품주문, HTML Form 사용
    - HTTP message body
        - HTTP API에서 주로 사용, JSON, XML, TEXT
        - 데이터 형식은 주로 JSON 사용
        - POST, PUT, PATCH
## HTTP 요청 데이터 - GET 쿼리 파라미터
- 검색, 필터, 페이징 등에서 많이 사용된다.
## HTTP 요청 데이터 - POST HTML Form
- 회원가입, 상품주문 등에서 많이 사용된다.
- 메세지 바디에 쿼리 파라미터 형식으로 데이터를 전달한다.
## HTTP 요청 데이터 - API 메시지바디 - 단순 텍스트
## HTTP 요청 데이터 - API 메시지바디 - JSON
- content-type 헤더에 `application/json`
## HttpServletResponse - 기본 사용법
- **HTTP 응답 메세지 생성**
    - HTTP 응답 코드 지정
    - 헤더생성
    - 바디생성
- **편의 기능 제공**
    - Content-Type, 쿠키, Redirect
## HttpServletResponse - 단순 텍스트, HTML
## HttpServletResponse - API JSON
# 3, 서블릿, JSP, MVC 패턴
## 회원관리 웹 애플리케이션 요구사항
## 서블릿으로 회원 관리 웹 애플리케이션 만들기
## JSP로 회원 관리 웹 애플리케이션 만들기
- `<% ~~ %>`, `<%= ~~ %>` 로 자바 코드를 실행/출력할 수 있다.
- 서블릿으로 개발할 때에는 뷰 화면을 위한 HTML을 만드는 작업이 자바 코드에 섞여서 지저분하고 복잡했다.
- JSP 사용하며 그 문제를 해결했지만..!!
- 다양한 비즈니스 로직 코드가 JSP에 노출되어있다.
- 즉, JSP가 너무 많은 역할을 한다.
## MVC 패턴 - 개요
- 하나에 대해 너무 많은 역할
- 변경의 라이프 사이클
- 각자의 기능에 맞도록 특화
- Model View Controller
    - 컨트롤러
    - 모델
    - 뷰
## MVC 패턴 - 적용
- redirect VS forward
- 컨트롤러와 view 로직 변경이 되었다!
## MVC 패턴 - 한계
- forward 중복
- 사용하지 않는 코드
    - request, response
- **공통 처리가 어렵다**

# 4. MVC 프레임워크 만들기
## 프론트 컨트롤러 패턴 소개
## 프론트 컨트롤러 도입 - v1
## View 분리 - v2
## Model 추가 - v3
- 서블릿 종속성 제거
- 뷰 이름 중복 제거
## 단순하고 실용적인 컨트롤러 - v4
## 유연한 컨트롤러1 - v5
- 어댑터 패턴을 이용한다.
    - 핸들러 어댑터
    - 핸들러
## 유연한 컨트롤러2 - v5
# 5. 스프링 MVC 구조 이해
## 스프링 MVC 전체 구조
- **직접 만든 프레임워크** -> **스프링 MVC 비교**
- FrontController -> DispatcherServlet
- handlerMappingMap -> HandlerMapping
- MyHandlerAdapter -> HandlerAdapter
- ModelView -> ModelAndView
- viewResolver -> ViewResolver
- MyView -> View
- **DispacherServlet 서블릿 등록**

- **동작 순서**
1. 핸들러 조회: 핸들러 매핑을 통해 요청 URL에 매핑된 핸들러(컨트롤러)를 조회한다.
2. 핸들러 어댑터 조회: 핸들러를 실행할 수 있는 핸들러 어댑터를 조회한다.
3. 핸들러 어댑터 실행: 핸들러 어댑터를 실행한다.
4. 핸들러 실행: 핸들러 어댑터가 실제 핸들러를 실행한다.
5. ModelAndView 반환: 핸들러 어댑터는 핸들러가 반환하는 정보를 ModelAndView로 변환해서
반환한다.
6. viewResolver 호출: 뷰 리졸버를 찾고 실행한다.
JSP의 경우: InternalResourceViewResolver 가 자동 등록되고, 사용된다.
7. View 반환: 뷰 리졸버는 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체를
반환한다.
JSP의 경우 InternalResourceView(JstlView) 를 반환하는데, 내부에 forward() 로직이 있다.
8. 뷰 렌더링: 뷰를 통해서 뷰를 렌더링 한다.

## 핸들러 매핑과 핸들러 어댑터
- **HandlerMapping**
    - 0순위: RequestMappingHandlerMapping: 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
    - 1순위: BeanNameUrlHandlerMapping: 스프링 빈의 이름으로 핸들러를 찾는다.
- **HandlerAdapter**
    - 0순위: RequestMappingHandlerAdapter: 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
    - 1순위: HttpRequestHandlerAdapter: HttpRequestHandler 처리
    - 2순위: SimpleControllerAdapter: Controller 인터페이스(애노테이션x, 과거에 사용) 처리

1. 핸들러 매핑으로 핸들러 조회
    1. HandlerMapping 을 순서대로 실행해서, 핸들러를 찾는다.
    2. 이 경우 빈 이름으로 핸들러를 찾아야 하기 때문에 이름 그대로 빈 이름으로 핸들러를 찾아주는 BeanNameUrlHandlerMapping 가 실행에 성공하고 핸들러인 OldController 를 반환한다.
2. 핸들러 어댑터 조회
    1. HandlerAdapter 의 supports() 를 순서대로 호출한다.
    2. SimpleControllerHandlerAdapter 가 Controller 인터페이스를 지원하므로 대상이 된다.
3. 핸들러 어댑터 실행
    1. 디스패처 서블릿이 조회한 SimpleControllerHandlerAdapter 를 실행하면서 핸들러 정보도 함께 넘겨준다.
    2. SimpleControllerHandlerAdapter 는 핸들러인 OldController 를 내부에서 실행하고, 그 결과를
반환한다.
## 뷰 리졸버
## 스프링MVC - 시작하기
## 스프링MVC - 컨트롤러 통합
