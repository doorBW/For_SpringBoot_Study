# Spring MVC 1

## 웹 어플리케이션 이해

### 웹 서버, 웹 애플리케이션 서버

- 웹 서버: 정적 리소스(파일) 제공
- WAS: 애플리케이션 로직 제공

### 서블릿

- 서블릿은 비즈니스 로직을 제외한 HTTP 통신(요청부터 응답까지)의 모든 것을 해준다.

- 서블릿 객체는 싱글톤으로 관리. 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근. 그러므로 공유 변수 사용 주의

### 동시 요청 - 멀티 쓰레드

- 하나의 쓰레드는 하나의 코드(요청)만 실행한다.

- 쓰레드 장단점

  - 장점
    - 동시 요청 처리
  - 단점
    - 생성 비용과 사용 리소스(컨텍스트 스위치 비용)가 비싸다
    - 제한이 없어서 CPU, 메모리 임계점을 넘어서 서버가 죽을 수 있다.

- 생성 비용을 줄이기 위해 쓰레드 풀이라는 기술을 쓴다. 쓰레드 풀은 쓰레드를 미리 생성 해놓고, 필요 할 때 사용과 반납을 반복한다.

### HTML, HTTP API, CSR, SSR

### 자바 백엔드 웹 기술 역사

## 서블릿

### 프로젝트 생성

롬복 등 필요한 플러그인 설치

### Hello 서블릿

서블릿을 실행하면 service protected 함수가 호출된다.

### HttpServletRequest 개요

임시 저장소 기능

- 저장: `request.setAttribute(name, value)`
- 조회: `request.getAttribute(name)`

### HttpServletRequest 기본 사용법

### HTTP 요청 데이터 - 개요

주로 3가지 방법으로 사용

- GET - 쿼리 파라미터

- POST - HTML Form

- HTTP message body

### HTTP 요청 데이터 - GET 쿼리 파라미터

- 검색, 필터, 페이징에서 많이 사용함

### HTTP 요청 데이터 - POST HTML Form

- `request.getParamter()`는 쿼리 파라미터와 POST Form 파라미터 둘 다 지원한다.

### HTTP 요청 데이터 - API 메세지 바디 - 단순 텍스트

### HTTP 요청 데이터 - API 메시지 바디 - JSON

- 보통 json 그대로 쓰지 않고 객체로 변경해서 사용한다.

- json을 자바 객체로 변환할려면, jackson, gson 같은 라이브러리를 사용해야 한다. 스프링 부투는 jackson을 기본적으로 제공해준다.

### HttpServletResponse - 기본 사용법

- HTTP 응답코드 지정
- 헤더 생성
- 바디 생성

### HTTP 응답 데이터 - 단순 텍스트, HTML

- 단순 텍스트 응답
- HTML 응답
- HTTP API - message body json

### HTTP 응답 데이터 - API JSON

- application/json은 스펙상 utf-8을 사용하다록 되어 있어서 따로 charset을 안 해줘도 된다.