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


