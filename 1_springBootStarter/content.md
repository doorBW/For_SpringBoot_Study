# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB접근기술
[강의링크](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/lecture/49603?tab=curriculum)

## 1. 프로젝트 환경설정
자바 11버전 기반으로 진행한다.   
스프링 부트 스타터 사이트로 스프링 프로젝트 설정   
https://start.spring.io   

maven, gradle 라이브러리 관리 툴.   
요새는 거의 gradle을 쓴다.   

### 라이브러리
스프링 부트 라이브러리   
- spring-boot-starter-web   
    - spring-boot-starter-tomcat: 톰캣 (웹서버)   
        - spring-webmvc: 스프링 웹 MVC   
    - spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)   
    - spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅   
        - spring-boot   
            - spring-core   
        - spring-boot-starter-logging   
            - logback, slf4j   
- 테스트 라이브러리   
    - spring-boot-starter-test   
        - junit: 테스트 프레임워크   
        - mockito: 목 라이브러리   
        - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리   
        - spring-test: 스프링 통합 테스트 지원   

### view 환경설정
스프링부트에서 resources/static/index.html 로 파일을 만들어두면, 자동으로 welcome page로 설정이된다.   

## 2. 스프링 웹 개발 기초
### MVC와 템플릿 엔진
MVC: Model, View, Controller

### API
String 반환 + @ResponseBody   
viewResolver 없이 단순 문자열만 반환됨   

Object + @ResponseBody   
객체가 json으로 반환됨   

- `@ResponseBody`
    - http의 body에 문자 내용을 직접 반환
    - `viewResolver` 대신 `HttpMessageConverter`가 동작
    - 기본 문자처리: `StringHttpMessageConverter`
    - 기본 객체처리: `MappingJackson2HttpMessageConverter`
    - byte 처리 등등 기타 여러 HttpMessageConverter 가 기본으로 등록되어 있음.

-> 참고: 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합하여 `HttpMessageConverter`가 선택된다. 

## 3. 회원관리예제 - 백엔드 개발
### 비즈니스 요구사항 정리
- 컨트롤러: 웹 MVC의 컨트롤러 역할
- 서비스: 핵심 비즈니스 로직 구현
- 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
- 도메인: 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨

### 회원 리포지토리 테스트 케이스 작성
- 테스트 케이스의 함수 실행 순서는 보장이 되지 않는다.   
-> @AfterEach annotation을 이용하여 각 테스트 함수가 종료된 후 clear처리 가능.   
