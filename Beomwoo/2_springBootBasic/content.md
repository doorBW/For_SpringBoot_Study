# 스프링 핵심 원리 - 기본편
[강의링크](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B8%B0%EB%B3%B8%ED%8E%B8)

## 1. 객체 지향 설계와 스프링
### 왜 스프링이 탄생했을까?
2000년 초반, EJB(Enterprise Java Beans). 좋았는데 비쌌다.   
-> EJB 지옥: 복잡하고 느리고 어렵다...   
-> 스프링과 하이버네이트의 탄생   

**JPA**   
JPA의 구현체들: 하이버네이트, EclipseLink, 기타...   
**현재의 시점에 스프링과 JPA모두 중요하다!**
   
### 스프링이란?
**스프링**
- 핵심 기술: 스프링 DI 컨테이너, AOP, 이벤트, 기타
- 웹 기술: 스프링 MVC, 스프링 WebFlux
- 데이터 접근 기술: 트랜잭션, JDBC, ORM 지원, XML 지원
- 기술 통합: 캐시, 이메일, 원격접근, 스케줄링
- 테스트: 스프링 기반 테스트 지원
- 언어: 코틀린, 그루비
    
**스프링 부트**
- 스프링을 편리하게 사용할 수 있도록 지원, 최근에는 기본으로 사용!
- 단독으로 실행할 수 있는 스프링app을 쉽게 생성
- Tomcat 같은 웹 서버를 내장해서 별도의 웹 서버가 필요하지 않다.
   
### 좋은 객체 지향 프로그래밍이란! 
- 추상화
- 캡슐화
- 상속
- 다형성   

"객체"들의 모임으로 파악하고자 하는 것.   
각각의 객체는 메세지를 주고 받고 데이터를 처리할 수 있다.   
프로그램을 유연하고 변경이 용이하게 만든다.   

**유연하고, 변경이 용이?**   
- 레고 블럭 조립하듯이
- 키보드, 마우스 갈아 끼우듯이
- 컴포넌트를 쉽고 유연하게 변경하면서 개발할 수 있는 방법

=> **!!다형성!!**

### 역할과 구현을 분리하자. 
- 역할과 구현으로 구분하면 세상이 단순해지고, 유연해지며 변경도 편리해진다.
- 장점?
    - 클라이언트는 대상의 역할(인터페이스)만 알면 된다.
    - 클라이언트는 구현대상의 내부 구조를 몰라도 된다.
    - 클라이언트는 구현 대상의 내부 구조가 변경되어도 영향을 받지 않는다.
    - 클라이언트는 구현 대상 자체를 변경해도 영향을 받지 않는다.

**in Java**
- 자바 언어의 다형성을 활용
    - 역할 = 인터페이스
    - 구현 = 인터페이스를 구현한 클래스, 구현 객체
- 객체를 설계할 때 역할과 구현을 명확히 분리
- 객체 설계시 역할(인터페이스)을 먼저 부여하고, 그 역할을 수행하는 구현 객체 만들기

**다형성의 본질**: 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경할 수 있다.   
   
### 스프링과 객체 지향
- 다형성이 가장 중요하다.
- 스프링은 다형성을 극대화해서 이용할 수 있게 도와준다.
- 스프링에서 이야기하는 제어의 역전(IoC), 의존관계 주입(DI)은 다형성을 활용해서 역할과 구현을 편리하게 다룰 수 있도록 지원한다.
- 스프링을 사용하면 마치 레고 블럭 조립하듯이, 구현을 편리하게 변경할 수 있다.
   
### SOLID !!!
- SRP 단일 책임 원칙
- OCP 개방-폐쇄 원칙   
확장에는 열려있으나, 변경에는 닫혀있어야 한다! -> **다형성**
- LSP 리스코프 치환 원칙   
프로그램의 객체는 프로그램의 **정확성**을 깨뜨리지 않으면서 하위타입의 인스턴스로 바꿀 수 있어야한다. -> 단순히 컴파일을 성공하는 것 이상을 이야기한다.
- ISP 인터페이스 분리 원칙   
범용적인 인터페이스 x, 인터페이스를 분리하여 인터페이스를 명확히하고 대체가능성을 높인다.
- DIP 의존관계 역전 원칙   
추상황에 의존해야지, 구체화에 의존하면 안된다.  
쉽게 이야기해서, 구현 클래스에 의존하지 말고 인터페이스에 의존하라는 것.
   
정리,   
- 객체 지향의 핵심은 다형성인데, 다형성만으로는 부족하다.
- 다형성 만으로는 OCP, DIP를 지킬 수 없다.

### 스프링 이야기에 왜 객체지향 이야기가?
- **스프링은 다음 기술로 다형성_OCP,DIP를 가능하게 지원한다.**
    - DI: 의존관계, 의존성 주입
    - DI 컨테이너

## 2. 예제 만들기
비즈니스 요구사항 파악 -> 설계 -> 개발   
member, order domain develop   

## 3. 객체 지향 원리 적용
### 새로운 할인 정책 적용과 문제점
```java
// OrderServiceImpl.java

private final MemberRepository memberRepository = new MemoryMemberRepository();
//private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
```
할인 정책을 변경하는데 클라이언트 코드(Impl)에도 영향을 준다. => DIP/OCP 위반   
? 그럼 어떻게..   
### 관심사의 분리
**AppConfig**   
```java
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
```
- 실제 동작에 필요한 구현 객체를 생성한다.
- 생성자를 통해서 주입
   
### 좋은 객체 지향 설계의 5가지 원칙 적용
- SRP: 하나의 클래스는 하나의 책임만 가져야 한다.
- DIP 의존관계 역전 원칙: 프로그래머는 추상화에 의존해야지, 구체화에 의존하면 안된다.   
의존성 주입은 이 원칙을 따르는 방법 중 하나다.
- OCP: 소프트웨어 요소는 확장에는 열려있으나 변경에는 닫혀 있어야 한다.   
소프트웨어 요소를 새롭게 확장해도 **사용 영역의 변경은 닫혀 있다.**

### IoC, DI, 컨테이너
1. 제어의 역전 Ioc(Inversion of Control)   

2. 의존관계 주입 DI(Dependency Injection)   
의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.   

3. Ioc 컨테이너, DI 컨테이너   
각각을 해주는 컨테이너 (like AppConfig)   

### 스프링으로 전환하기!
- `ApplicationContext`를 스프링 컨테이너라고 한다.
- 기존에는 개발자가 `AppConfig`를 통해 직접 객체를 생성하고 DI를 했지만, 이제는 스프링 컨테이너를 통해 사용한다.

## 4. 스프링 컨테이너와 스프링 빈
### 스프링 컨테이너 생성
- `ApplicationContext` 를 스프링 컨테이너라고 한다.
- `ApplicationContext` 는 인터페이스다.
- 빈 이름은 항상 다른 이름을 부여해야 한다.
같은 이름을 부여하면 경우에 따라 다른 빈이 무시되거나 덮어지거나 오류가 발생할 수 있다.

### Bean 조회
- 기본 조회 : `ac.getBean()`
- 동일한 타입이 둘 이상 : `ac.getBeansOfType`
- 상속관계
부모 타입으로 조회하면, 자식 타입도 함께 조회된다.

### BeanFactory, ApplicationContext
BeanFactory <- ApplicationContext <- AnnotationConfig, ApplicationContext, ...   
- `BeanFactory`   
스프링 컨테이너의 최상위 인터페이스다.   
스프링 빈을 관리하고 조회하는 역할을 담당한다.   
- `ApplicationContext`   
BeanFactory 을 모두 상속받아서 제공한다.   
\+ 메시지 소스를 활용한 국제화 기능   
\+ 환경변수 : 로컬,개발,운영등을 구분해서 처리   
\+ 애플리케이션 이벤트 : 이벤트를 발행하고 구독하는 모델을 편리하게 지원   
\+ 편리한 리소스 조회 : 파일,클래스패스,외부 등에서 리소스를 편리하게 조회   
   
-> BeanFactory를 직접 사용할 일은 거의 없다. 부가기능이 포함된 ApplicationContext를 사용한다.   

### 스프링 빈 설정 메타정보 - BeanDefinition
- `BeanDefinition`을 빈 설정 메타정보라고 한다.   
스프링 컨테이너는 정보가 자바코드인지, XML인지 몰라도 된다. 오직 BeanDefinition만 알면된다.   
즉 이것 또한 **역할과 구현을 개념적으로 나눈 것**이다.   
- 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다.   
- 새로운 형식의 설정 정보가 추가되면 `xxxBeanDefinitionReader`(AnnotatedBeanDefinitionReader, XmlBeanDefinitionReader, ...)를 만들어서 `BeanDefinition`을 생성한다.   

## 5. 싱글 톤 컨테이너
### 싱글톤 패턴
- 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴   
### 싱글톤 패턴의 문제점
- 싱글톤 패턴을 구현하는 코드가 많아진다.
- 의존관계상 클라이엍느가 구체 클래스에 의존하게 된다. -> DIP를 위반한다.
- 내부 속성을 변경하거나 초기화 하기 어렵다.
- private 생성자로 자식 클래스를 만들기 어렵다.
- 유연성이 떨어진다.
- 안티패턴으로 불리기도 한다.
### 싱글톤 컨테이너
- 스프링 컨테이너는 싱글턴 패턴을 직접 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
- 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다.
- 스프링의 기본 빈 등록 방식은 싱글톤이지만, 싱글톤 방식만 지원하는 건 아님.   
요청할때마다 새로운 객체를 생성해서 반환하는 기능도 제공한다.
### 싱글톤 방식의 주의점
- 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.   
객체 인스턴스를 하나만 생성해서 여러 클라이언트가 공유하기 때문에,
- 무상태(stateless)로 설계해야 한다
    - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
    - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
    - 가급적 읽기만 가능해야 한다.
    - 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.

### @Configuration
```java
@Bean memberService -> new MemoryMemberRepository();
@Bean orderService ->  new MemoryMemberRepository();
```
- 각각 다른 MemberRepository가 생기는게 아닐까?
- Test해보니 아닌데..?
- 실제로 AppConfig 인스턴스가 빈에 등록되는게 아니라, 스프링이 해석한 , AppConfig를 오버라이드한 AppConfig@CGLIB가 등록된다.
- 그리고, @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
- 덕분에 싱글톤이 보장된다!

## 6. 컴포넌트 스캔
- 컴포넌트 스캔 위치: `basePackages, basePackageClasses`   
`@ComponentScan`이 붙은 설정 정보 클래스의 패키지 시작 위치.
- 컴포넌트 스캔 기본 대상   
실제 내부를 살펴보면 `@Component`가 들어가있다.
    - `@Component`
    - `@Controller`
    - `@Service`
    - `@Repository`
    - `@Configuration`   
- ComponentScan의 FilterType options
    - ANNOTATION: 기본 값, 어노테이션을 인식해서 동작
    - ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작
    - ASPECTJ: AspectJ 패턴 사용
    - REGEX: 정규표현식
    - CUSTOM: `TypeFilter` 라는 인터페이스를 구현해서 처리

### 중복 등록과 충돌
- 자동 빈 등록 vs 자동 빈 등록   
`ConflictingBeanDefinitionException` 예외 발생
- 수동 빈 등록 vs 자동 빈 등록   
이 경우 수동 빈 등록이 우선권을 가진다.   
-> 자동빈을 수동빈이 오버라이딩 해버린다.   
물론 개발자가 의도적으로 한 것이라면 괜찮(?)겠지만, 의도가 아니라면 **정말 잡기 어려운 버그가 만들어진다.**   
-> 그래서 최근 스프링 부트에서는 디폴트로, 수동빈등록과 자동빈등록이 중복되면 에러가 나도록 바뀌었다.

## 7. 의존관계 자동 주입
### 다양한 의존관계 주입 방법
- 생성자 주입
```java
    ...

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    ...
```   
- 
    - 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
    - **불편, 필수** 의존관계에 사용
    - **중요! 생성자가 1개만 있으면** `@Autowired`를 생략가능
- 수정자 주입(setter 주입)
    - setter 메서드에 `@Autowired` 붙여서 적용
    - **선택, 변경** 가능성이 있는 의존관계에 사용
- 필드 주입
    - 코드가 간결하지만, 외부에서 변경이 불가능하여 테스트하기 힘들다는 치명적인 단점이 있다.
    - DI 프레임워크가 없으면 아무것도 할 수 없다.
    - 스프링 설정을 목적으로 하는 어플리케이션 코드가 아니라 테스트코드 / `@Configuration` 같은 곳에서만 특수하게 사용.
- 일반 메서드 주입
    - 한번에 여러 필드를 주입할 수 있다.
    - 하지만 일반적으로 잘 사용하지 않는다.

### 옵션 처리
- `@Autowired` 만 사용하면 required 옵션의 기본값이 true로 되어 있어서, 자동 주입 대상이 없으면 오류가 발생한다.
```java
// 메서드 호출 자체가 안된다.
@Autowired(required = false)
public void setNoBean1(Member noBean1){
    System.out.println("noBean1 = " + noBean1);
}

// 자동 주입할 대상이 없으면 null이 입력된다.
@Autowired
public void setNoBean2(@Nullable Member noBean2){
    System.out.println("noBean2 = " + noBean2);
}

// 자동 주입할 대상이 없으면 `Optional.empty` 가 입력된다.
@Autowired
public void setNoBean3(Optional<Member> noBean3){
    System.out.println("noBean3 = " + noBean3);
}
```

### 생성자 주입을 선택해라.
- 최근에는 스프링을 포함한 DI프레임워크 대부분이 생성자 주입을 권장한다. 왜?
1. **불변**   
    - 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다.
    - 수정자 주입을 사용하면 setter메서드를 Public 으로 열어두어야 한다.
    - 변경하면 안되는 메서드를 열어두는 것은 좋지 않다.
    - 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다.
    - 따라서 불변하게 설계할 수 있다.
2. **누락**
- 생성자 주입을 사용하면 `final`키워드를 사용할 수 있다. 그래서 생성자에서 혹시라도 초기화 되지 않는 오류를 컴파일 시점에 막아준다.
- 수정자 주입을 퐇마한 나머지 주입 방식은 모두 생성자 이후에 호출되므로 필드에 `final` 키워드를 사용할 수 없다.

### 롬복과 최신 트렌드

### 조회 빈이 2개 이상 - 문제
- 해결방안
    - `@Autowired` 필드 명 매칭
    - `@Quilifier`
        - 추가 구분자를 붙여주는 방법.
    - `@Primary`
        - 우선권을 지정하는 방법

### 자동, 수동의 올바른 실무 운영기준
- 업무로직빈 / 기술지원빈 두개로 나누어서 생각하자.
- 어플리케이션에 광범위하게 영향을 미치는 **기술 지원 객체**는 수동 빈으로 등록해서, 설정정보에 바로 나타나게 하는 것이 유지보수 하기에 좋다.

## 8. 빈 생명주기 콜백
- (간단하게)스프링 빈의 라이프 사이클   
객체 생성 -> 의존관게 주입   
- 스프링 빈의 이벤트 라이프사이클   
**스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료**   
- 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
- 소멸전 콜백: 빈이 소멸되기 직전에 호출

**객체의 생성과 초기화를 분리하자.**   
**스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원한다.**
- 인터페이스(InitializingBean, DisposableBean)
- 설정 정보에 초기화 메서드, 종료 메서드 지정
- @PostConstruct, @PreDestory 애노테이션 지원

### 초기화, 소멸 인터페이스 단점
- 이 인터페이스는 스프링 전용 인터페이스다. 해당 코드가 스프링 전용 인터페이스에 의존한다.
- 초기화, 소멸 메서드의 이름을 변경할 수 없다.
- 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.
- 인터페이스를 사용하는건 초창기이고 지금은 더 나은 방법들이 있다.


