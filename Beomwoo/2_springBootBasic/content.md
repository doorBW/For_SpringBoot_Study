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
