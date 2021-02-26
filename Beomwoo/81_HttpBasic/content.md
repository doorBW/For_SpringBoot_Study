# 모든 개발자를 위한 HTTP 웹 기본지식
[강의링크](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC/dashboard)

## 인터넷 네트워크
### 인터넷 통신
클라이언트 <-[인터넷]-> 서버   
how?
### IP(Internet Protocol)
- 인터넷에서 메세지 전송 how?
- IP 주소를 부여한다!
- 지정한 IP주소(IP Adrress)로 전달
- IP 패킷 정보
    - 출발지 IP
    - 목적지 IP
    - data
    - ...
- 답장의 개념으로, 서버 패킷 전달
- IP 프로토콜의 한계
    - 비연결성
        - 패킷이 받을 대상이 없거나 서비스 불능 상태여도 패킷 전송
    - 비신뢰성
        - 중간에 패킷이 사라지면?
        - 패킷이 순서대로 안오면?
    - 프로그램 구분
        - 같은 IP를 사용하는 서버에서 통신하는 애플리케이션이 둘 이상이면?
### TCP / UDP
- IP 프로토콜의 한계를 해결한다.
- 인터넷 프로토콜 스택의 4계층
    - 애플리케이션 계층: HTTP, FTP
    - 전송 계층: TCP, UDP
    - 인터넷 계층: IP
    - 네트워크 인터페이스 계층
- TCP 특징
    - 전송 제어 프로토콜
    - 연결지향: TCP 3 way handshake
    - 데이터 전달 보증
    - 순서 보장
    - 신뢰할 수 있는 프로토콜
    - 현재는 대부분 TCP 사용
- UDP 특징
    - 사용자 데이터그램 프로토콜
    - 기능이 거의 없음
    - 연결지향 - TCP3Way handshake X
    - 데이터 전달 보증 x
    - 순서 보장 x
    - 데이터 전달 및 순서가 보장되지 않지만, 단순하고 빠름
    - IP와 거의 같다 +PORT +체크섬 정도만 추가
    - 애플리케이션에서 추가 작업 필요
### PORT
- 한번에 둘 이상 연결해야 하면?
- PORT: 같은 IP내에서 프로세스 구분!
- 0~65535: 할당가능
- 0~1023: 잘 알려진 포트, 사용하지 않는 것이 좋음.
    - FTP: 20, 21
    - TELNET: 23
    - HTTP: 80
    - HTTPS: 443
### DNS
- IP는 기억하기 어렵다.
- DNS(Nomain Name System)를 이용한다.
- DNS서버에서 도메인명<->IP연결
- 클라이언트는 도메인명으로 요청을 한다.

## URI와 웹 브라우저 요청 흐름
### URI
- URI = URL + URN + ...
- URL: Resource Locator
- URN: Resource Name
- URI: Resource Identifier

### 웹 브라우저 요청 흐름

## HTTP 기본
- HyperText Transfer Protocol
- HTML, TEXT, Image, 음성, 영상, 파일, JSON, XML, ...
- TCP: HTTP/1.1, HTTP/2
- UDP: HTTP/3
- 현재는 HTTP/1.1 주로 사용(HTTP/2, HTTP/3도 점점 증가)
### HTTP 특징
1. 클라이언트 서버 구조
    - Request / Response 구조
2. 무상태 프로토콜(스테이스리스), 비연결성
    - Stateless
        - 서버가 클라이언트의 상태를 유지하지 않는다.
        - 실무한계: 로그인등.. -> 일반적으로 브라우저 쿠키와 서버 세션등을 사용해서 상태 유지
    - Connectionless
        - 최소한의 자원 유지 -> 서버자원을 효율적으로 사용한다.
        - HTTP는 기본이 연결을 유지하지 않는 모델
        - HTTP 지속 연결(Persistent Connections): 여러가지 자원을 받는 동안 연결을 유지한다.
3. HTTP 메시지
4. 단순함, 확장 가능