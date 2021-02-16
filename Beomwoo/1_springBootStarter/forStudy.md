## 스프링 테스트 관련 어노테이션
- @Test
- @Ignore
- @Before / @After
- @BeforeClass / @AfterClass
- @RunWith(SpringJUnit4ClassRunner.class)
- @ContextConfiguration(locations = "classpath:xml파일위치")
- @EnableAutoConfiguration

## 컴포넌트 기본 탐색 대상 어노테이션
- @Component
- @Controller
- @Service
- @Repository
- @Configuration
- @RestController   
json형태로 객체 데이터를 반환
- @ControllerAdvice   
대체로 예외처리기로 사용한다.   
만약 예외처리를 body로 전달하고 싶다면, @ResponseBody를 합쳐놓은 @RestControllerAdvice를 사용하면 된다.
- @ManagedBean   
???
- @Named
???

## Bean Scope
- 빈 스코프 : 빈 생존기간
- 기본적으로 DI컨테이너에 빈이 싱글톤으로 주입되는 것은, 빈 스코프의 디폴트 값이 singleton이라서 이다.
- Bean scope 종류
    - singleton
    - prototype
    - request
    - session
    - global session
    - application
    - custom
- How to chansge bean scope
```java
@Bean
@Scope("prototype")
public UserService userService(){
    return new UserServiceImpl();
}
```

---
## Refer
- https://codevang.tistory.com/259