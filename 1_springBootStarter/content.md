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

## 4. 스프링 빈과 의존관계
### 컴포넌트 스캔방식
- `@Autowired`가 생성자에 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라고 한다.   
-> 객체를 스프링컨테이너에서 가져온다.   
근데 순수한 자바클래스와 같은건 스프링컨테이너에 안올라가있다!   
`@Component`(`@Controller`,`@Service`,`@Repository`얘네는 컴포넌트 어노테이션을 포함하고있다.)를 넣어주면 스프링 컨테이너에 들어가있는다.   
-> **컴포넌트 스캔방식**   
   
? 아무때나 @Component를 넣어도 되나?   
SpringApplication 하위는 자동으로 찾아서 등록한다. 하위패키지가 아니면 컴포넌트스캔을 안한다. -> 따로 설정을 하면 되긴 됨   
   
- 스프링은 스프링컨테이너에 스프링 빈을 등록할때, 기본으로 싱글톤으로 등록함.(유일하게 하나만 등록하고 공유함.) 즉, 같은 스프링 빈이면 모두 같은 인스턴스이다, 물론 설정으로 싱글톤이 아니게 설정할 수 있다.   

### 자바 코드로 직접 스프링 빈 등록하기
- `Configuration`을 가지는 클래스를 만들고, `@Bean` 을 이용해서 직접 스프링 빈에 등록해준다.
ex)
```java
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepository();
    }
}
```

컴포넌트 스캔방식이 편할테지만, 각각 장단점이 있다.   

## 5. 웹 MVC 개발
- "/" 에 대한 우선순위에서 웰컴페이지는 후순위를 가진다.

## 6. 스프링 DB 접근 기술
- 교육용으로 h2 사용
- Config를 통해 기존의 memoryRepo에서 jdbcRepo로 변경하는데, application의 config파일에 대한 변경 이외의 변경이 없다! -> 스프링의 장점 중 하나.   

### 스프링 통합 테스트
`@SpringBootTest`: 스프링 컨테이너와 테스트를 함께 실행   
`@Transactional`: 테스트케이스에 이 어노테이션이 있으면, 테스트시작전 트랜잭션을 시작하고, 테스트완료시 롤백한다.   

### JdbcTemplate
- `JdbcTemplate`를 사용한다.
- 생성자에, new JdbcTemplate(dataSource)
- document를 보면 자세하게 잘 나와있다.
- 아래와 같은 식으로 사용된다.
```java
public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>(); parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        member.setId(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);

        return result.stream().findAny();
    }
    
    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);

        return result.stream().findAny();
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member(); member.setId(rs.getLong("id"));
            member.setName(rs.getString("name")); return member;
        };
    }
}
```

### JPA
- `@Entity` : JPA에서 관리하는 엔터티
- `@Id`, `@Column` 으로 db와 연결

### 스프링 데이터 JPA
- JPA를 도와주는 도구이다. JPA를 모르고 쓰면 많은 문제들을 해결하지 못할 수 있다.
- 메서드 이름만으로 조회 기능 제공
- 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용한다.