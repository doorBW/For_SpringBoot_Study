# IoC ( Inversion of Control ) 

좋은 객체 지향 프로그래밍을 하기 위해서는 IoC 기술이 필요하고, 이 기술을 Spring 에서는 DI ( Dependency Injection ) 이라는 이름으로 지원해주고 있다. 

IoC ( Inversion of Control ) 의 개념과 스프링 DI ( Dependency Injection ) 을 정리하기 전에 먼저, 객체 지향 프로그래밍이 무엇인지,  좋은 객체 지향 설계란 무엇인지 알아보자. 그 후, 예시를 통해 IoC 기술의 등장 배경과 스프링 DI 에 대해서 정리하자. 

<br>

### 1) 객체 지향 프로그래밍 ( OOP )

- 객체 지향 프로그래밍은  컴퓨터 프로그램을 명령어의 목록으로 보는 시각에서 벗어나 여러 개의 독립된 단위(객체)의 모임으로 파악하는 것이다. 각각의 객체는 메시지를 주고받고 데이터를 처리할 수 있다.
  - 레고 블럭 조립하듯이 컴포넌트를 쉽고 유연하게 변경하면서 개발할 수 있는 방법을 의미한다.
- 객체 지향 프로그래밍은 프로그램을 **유연하고 변경이 용이**하게 만들기 때문에 소프트웨어 개발에 많이 사용된다.

<br>

### 2) 다형성 ( Polymorphism )

다형성이란 세계를 역할과 구분으로 나눠서 생각하는 것이다.

<img src="https://user-images.githubusercontent.com/59816811/104278262-a78cc380-54eb-11eb-8645-47cc5dec37d2.png" alt="20210112_153417" width="500" />

예시를 통해서 알아보자.

"자동차" 를 자동차 역할(인터페이스) / 자동차 구현(인터페이스를 구현한 클래스) 으로 나눠서 생각해보자.

자동차 역할이라는 틀이 정해져있고, 그 틀에 맞춰서 여러 자동차 회사들이 자동차 기능을 구현한다. 그러면 운전자 입장에서 생각해보자. 자동차 역할에 맞춰서 운전자 기능을 구현해놓는다면, 자동차 구현이 바뀌더라도 운전자는 상관없이 자동차를 운전할 수 있다.

**--> "운전자" 가 자동차 역할에 맞춰서 운전자 기능을 구현해 놓는다면, "운전자"는 자동차 구현(K3, 아반떼, 테슬라 등등)에 영향을 받지 않는다.** 

**--> 클라이언트(운전자)가 자동차의 내부 구조를 몰라도 되고, 자동차가 내부적으로 바뀌더라도 영향을 주지 않는다.**

**--> 유연하고 변경이 용이하고 확장이 가능하다.**

<br>

### 3) 다형성 in Java

- 자바 언어의 다형성은 오버라이딩, 오버로딩을 통해 나타남.
- 역할 = 인터페이스
- 구현 = 인터페이스를 구현한 클래스 ( 구현 객체 )
- 객체 설계 시 역할과 구현을 명확히 분리해서 역할(인터페이스)를 먼저 부여하고, 그 역할을 수행하는 구현 객체를 만들면 된다. 

<br>

### 4)  IoC 등장 배경

```java
public class Member {
	private Long id;
	private String name;
    // ~~ getter & setter ~~ 
}
```

```java
public interface MemberRepository {
    // ~~ 핵심 비지니스 로직 ~~
}
```

```java
public class MemoryMemberRepository implements MemberRepository {
    // ~~ 핵심 비지니스 로직 ~~
}
```

회원 정보 Member 객체와 회원 정보를 다루는 역할에 해당하는 MemberRepository 인터페이스, 구현에 해당하는 MemoryMemberRepository 클래스가 위와 같이 있다.

<img src="https://user-images.githubusercontent.com/59816811/104283282-a8295800-54f3-11eb-8da8-64977d78da1e.png" alt="20210112_163131" width="400" />

객체 지향의 특징을 이용해서 역할 / 구현 을 잘 구분해 놓았다. 

```java
public class MemberService {
     private MemberRepository memberRepository = new MemoryMemberRepository();
}
```

이제 잘 구현해놓은 MemoryMemberRepository 클래스를 MemberService에서 이용하던 중에 더 좋은 JDBCMemberRepository 클래스가 등장해서 변경한다고 해보자.  

<img src="https://user-images.githubusercontent.com/59816811/104283821-782e8480-54f4-11eb-954e-1c021e370403.png" alt="20210112_163724" width="400" />

역할 / 구현을 구분해놓았기 때문에 간단한 코드 수정으로 변경이 가능한 것을 볼 수 있다.

```java
// 변경 전
private MemberRepository memberRepository = new MemoryMemberRepository();

// 변경 후
private  MemberRepository memberRepository = new JDBCMemberRepository();
```

**하지만, 큰 문제가 남아있다. 구현 객체를 변경하려면 클라이언트인 MemberService 안의 코드를 변경해야한다. 따라서, OCP 원칙에 위배되고 MemberService 클라이언트가 직접 구현 클래스를 선택하므로 DIP 원칙에 위배된다.** 

<img src="https://user-images.githubusercontent.com/59816811/104392941-95139800-5586-11eb-9996-f7d06112a02f.png" alt="spring_whatisIOC"  />

위의 그림과 같은 상황인거다. 내가 원한건 MemberService 가 Interface(역할) 인 MemberRepository 에 의존한다고 생각했는데 실제로는 Class(구현 객체)를 의존하고 있는 상황이다. 

**--> 역할 / 구현 구분 만으로는 좋은 객체 지향 프로그래밍이 불가능...!!!**

**--> Interface 에 의존하게 만들고 실제 구현 객체는 외부에서 삽입해주자!**

```java
// 어떤 memberRepository 를 사용할지는 클라이언트가 결정 X
private MemberRepository memberRepository;
```

<br>

### 5) IoC 

위의 똑같은 상황을 IoC 를 이용해서 해결해보겠습니다.

```java
public class AppConfig {

    public MemberService memberService() {
   		return new MemberServiceImpl(memberRepository());
    }
    
    public MemberRepository memberRepository() {
    	return new MemoryMemberRepository();
    }
}
```

MemberService, MemberRepository 객체를 생성하고 어떤 의존 관계를 가지고 있는지 관리하는 AppConfig 클래스를 따로 만듭니다. AppConfig 오브젝트를 통해 MemberService 객체를 생성하고 사용하면 우리는 클라이언트가 직접 객체의 생성 방법을 결정할 필요 없이 다음과 같이 코드를 구현할 수 있습니다.

```java
public class MemberService {
     private MemberRepository memberRepository;
     
     public MemberService(MemberRepository memberRepository){
     	this.memberRepositry = memberRepository;
     }
}
```

즉, MemberService 에서 어떤 MemberRepository 구현체를 이용하는지 신경쓰지 않고 MemberRepository 라는 역할만을 신경쓸 수 있습니다. 

<br>

**좋은 객체 지향 설계를 위해 클라이언트가 직접 객체를 생성하는게 아니라, 객체의 생성 방법을 결정하고 그렇게 만들어진 객체를 돌려주는 일을 하는 오브젝트를 따로 만듭니다. 즉, 클라이언트가 자신이 사용할 오브젝트를 스스로 선택하지 않고, 생성하지도 않고 모든 제어 권한을 자신이 아닌 다른 대상에게 위임합니다.**

**제어 권한을 다른 사람에게 위임한다고해서 Inversion of Control (IOC) 라고 불리고, 다른 의미로 객체를 외부로부터 주입받는다고 해서 스프링에서는 DI (Dependency Injection) 이라고도 부릅니다.**

