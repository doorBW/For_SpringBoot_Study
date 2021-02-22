# 자바 ORM 표준 JPA 프로그래밍 - 기본편
[강의링크](https://www.inflearn.com/course/ORM-JPA-Basic#)

## JPA 소개 - **중요!!!**
### SQL 중심적인 개발의 문제점
- 기존에는 SQL 중심의 개발에서 벗어나기 어렵다!

### JPA 소개
- Java Persistance API
- Object-relational mapping
- 객체는 객체대로 설계, 관계형 데이터베이스는 관계형 데이터베이스대로 설계
- ORM 프레임워크가 중간에서 매핑
- JPA는 인터페이스의 모음(하이버네이트, EclipseLink, DataNucleus)
- SQL 중심적인 개발에서 객체 중심으로 개발이 가능해진다.

## JPA 시작하기
### H2 DB Setting
### Java project (with Maven)

## 영속성 관리 - 내부 동작 방식
### JPA에서 가장 중요한 2가지
- 객체와 관계형 데이터베이스 매핑하기
- **영속성 컨텍스트**

### 영속성 컨텍스트
- '엔터티를 영구 저장하는 환경'이라는 뜻

### 엔티티의 생명주기
- 비영속(new/transient)   
영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
- 영속(managed)   
영속성 컨텍스트에 관리되는 상태
- 준영속(detached)   
영속성 컨텍스트에 저장되었다가 분리된 상태
- 삭제(removed)   
삭제된 상태

### 영속성 컨텍스트의 이점
- 1차 캐시
- 동일성(identity) 보장
- 트랜잭션을 지원하는 쓰기 지연
- 변경 감지
- 지연 로딩

