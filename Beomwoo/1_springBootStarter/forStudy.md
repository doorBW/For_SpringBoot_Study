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

---
## Refer
- https://codevang.tistory.com/259