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

## HTTP method
- URI설계
    - 가장 중요한 것은 **리소스 식별**
    - 리소스?
        - ex) 미네랄을 캐라 -> 미네랄이 리소스!
        - **회원** 목록 조회 /members
        - **회원** 조회 /members/{id}
        - **회원** 등록 /members/{id}
        - **회원** 수정 /members/{id}
        - **회원** 삭제 /members/{id}
        - 조희/등록/수정/삭제 => 어떻게 구분해?
    - **URI는 리소스만 식별한다.** -> 행위를 식별하는게 아니다!
    - **리소스**와 해당 리소스를 대상으로 하는 **행위**를 분리해야 한다.
        - 리소스: 회원
        - 행위: 조회, 등록, 삭제, 변경
- HTTP Method
    - GET: 리소스 조회
        - 서버에 전달하고 싶은 데이터는 Query(쿼리 파라미터, 쿼리스트링)를 통해서 전달
        - 바디에 데이터를 담는건 가능하지만, 권장하지 않는다.(지원하지 않는 곳도 있음..)
    - POST: 요청 데이터 처리, 주로 등록에 사용
        - 메시지 바디를 통해 서버로 요청 데이터 전달
        - 서버는 요청 데이터를 처리
        - 주로 전달된 데이터로 신규 리소스 등록, 프로세스 처리에 사용
    - 1. 새 리소스 생성(등록)
    - 2. 요청 데이터 처리
        - 단순히 데이터 생성이나 변경을 넘어서 프로세스를 처리하는 경우
    - 3. 다른 메서드로 처리하기 애매한 경우
        - 애매하면 POST
    - PUT: 리소스를 대체, 해당 리소스가 없으면 생성
        - 리소스를 대체
            - 있으면 대체, 없으면 생성, 즉 덮어버린다.
        - 중요! **클라이언트가 리소스를 식별**
            - 클라이언트가 리소스 위치를 알고 URI를 지정
            - 이것이 POST와 차이점
    - PATCH: 리소스 부분 변경
        - 리소스 부분 변경
    - DELETE: 리소스 삭제
        - 리소스 제거
- HTTP 메서드의 속성
    - 안전(Safe Methods)   
    호출해도 리소스를 변경하지 않는다.
    - 멱등(Idempotent Methods)   
    한번 호출하든 두번 호출하든 100번 호출하든 결과가 똑같다.
    - 캐시가능(Cacheable Methods)
    
## HTTP Method 활용
### 클라이언트에서 서버로 데이터 전송
1. 쿼리 파라미터를 활용한 데이터 전송
    - GET
    - 주로 정렬 필터
2. 메세지 바디를 이용한 데이터 전송
    - POST PUT PATCH
- 크게 4가지 상황!
    1. 정적 데이터 조회
    2. 동적 데이터 조회
    3. HTML Form을 통한 데이터 전송
    4. HTML API를 통한 데이터 전송
    
### HTTP API 설계 예시
- 회원 목록 /members -> GET
- 회원 등록 /members -> POST
- 회원 조회 /members/{id} -> GET
- 회원 수정 /members/{id} -> PATCH, PUT, POST
- 회원 삭제 /members/{id} -> DELETE

## HTTP 상태코드
- 클라이언트가 보낸 요청의 처리 상태를 응답에서 알려주는 기능
### 1xx (Informational): 요청이 수신되어 처리중
- 거의 사용 안함
### 2xx (Successfull): 요청 정상 처리
- 200 OK: 요청 성공
- 201 Created: 요청 성공해서 새로운 리소스가 생성됨
- 202 Accepted: 요청이 접수되었으나 처리가 완료되지 않았음.
- 204 No Content: 서버가 요청을 성공적으로 수행했지만, 응답 페이로드 본문에 보낼 데이터가 없음.(ex. 웹 문서 편집기에서 save버튼)

### 3xx (Redirection): 요청을 완료하려면 추가 행동이 필요
- 리다이렉션의 이해
    - 웹 브라우저는 3xx 응답의 결과에 location이 있으면 location으로 이동한다.
    - 영구 리다이렉션 - 특정 리소스의 URI가 영구적으로 이동
    - 일시 리다이렉션 - 일시적인 변경
    - 특수 리다이렉션- 결과 대신 캐시를 사용
- 영구 리다이렉션
    - 301 Moved Permanently
        - 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음.
    - 308 Permanent Redirect
        - 리다이렉트시 요청 메서드와 본문 유지
- 일시적인 리다이렉션
    - 302 Found
        - 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음.(MAY, 명확하지 않음.)
    - 307 Temporary Redirect
        - 리다이렉트시 요청 메서드와 본문 유지
    - 303 See Other
        - 리다이렉트시 요청 메서드가 GET으로 변경
    - **그래서 뭘 써야 하나요?** 302, 307, 303
        - 302 -> GET으로 변할 수 있음
        - 307 -> 메서드가 변하면 안됨
        - 303 -> 메서드가 GET으로 변경

- 300 Multiple Choices: 안쓴다.
- 304 Not Modified
    - 캐시를 목적으로 사용
    - 클라이언트에게 리소스가 수정되지 않았음을 알려준다. 따라서 클라이언트는 로컬PC에 저장된 캐시를 재사용한다.
    - 304 응답은 응답에 메시지 바디를 포함하면 안된다.
    - 조건부 GET, HEAD 요청시 사용

### 4xx (Client Error): 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없음
- 오류의 원인이 클라이언트에 있음.
- 401 Unauthorized: 클라이언트가 해당 리소스에 대한 인증이 필요함.
    - WWW-Authenticate 헤더와 함께 인증 방법을 설명
- 403 Forbidden: 서버가 요청을 이해했지만 승인을 거부함
- 404 Not Found: 요청 리소스를 찾을 수 없음

### 5xx (Server Error): 서버 오류, 서버가 정상 요청을 처리하지 못함.
- 500 Internal Server Error: 서버 문제로 인한 이용불가
- 503 Service Unavailable: 서비스 이용불가
    - Retry-After 헤더필드로 얼마뒤에 복구되는지 보낼수도 있음

### 만약 모르는 상태 코드가 나타나면?
- 클라이언트는 상위 상태코드로 해석함. -> 백번대를 보고!
- -> 미래에 새로운 상태 코드가 추가되어도 클라이언트를 변경하지 않아도 된다.

## HTTP 헤더
### 일반헤더
- HTTP전송에 필요한 모든 부가 정보
- 표준 헤더가 너무 많음
- 필요시 임의의 필더도 추가 가능
### 과거에는,,
- 헤더 분류
    - General 헤더: 메시지 전체에 적용되는 정보
    - Request 헤더: 요청정보
    - Response 헤더: 응답정보
    - Entity 헤더: 엔터티 바디 정보   
    -> 엔티티 본문의 데이터를 해석할 수 있는 정보 제공: 데이터 유형(html, json), 데이터 길이, 압축정보 등등
### RFC723x 변화
- 엔티티라는 단어가 사라짐
- 메시지 본문을 통해 표현 데이터 전달
- 메시지 본문 = 페이로드(payload)
- 표현은 요청이나 응답에서 전달할 실제 데이터
- 표현 헤더는 표현 데이터를 해석할 수 있는 정보 제공
### 표현
- Content-Type: 표현 데이터의 형식
- Content-Encoding: 압축 방식
- Content-Languate: 자연 언어
- Content-Length: 길이
- 표현 헤더는 전송, 응답 둘다 사용
### 협상(Content-negotiation)
- Accpet: 클라이언트가 선호하는 미디어 타입 전달
- Accpet-Charset: 클라이언트가 선호하는 문자 인코딩
- Accpet-Encoding: 클라이언트가 선호하는 압축 인코딩
- Accpet-Language: 클라이언트가 선호하는 자연 언어
### 협상과 우선순위(Quality Values(q))
- Quality Values(q) 값 사용
- 0~1, 클수록 높은 우선순위
- 생략하면 1
- 구체적인 것이 우선한다.
    - Accept: text/*, text/plain, text/plain;format-flowed, */*
    1. text/plain;format=flowed
    2. text/plain
    3. text/*
    4. */*
### 전송방식
- 단순 전송
    - 그냥 요청하면 응답을 준다.
- 압축 전송
    - Content-Encoding
- 분할 전송
    - Transfer-Encoding
- 범위 전송
    - Range, Content-Range
### 일반정보
- From: 유저 에이전트의 이메일 정보
    - 일반적으로 잘 사용되지 않음.
    - 검색엔진 같은 곳에서 주로 사용
- Referer: 이전 웹페이지 주소
- User-Agent: 유저 에이전트 애플리케이션 정보
    - 클라이언트의 애플리케이션 정보(웹 브라우저 정보 등)
- Server: 요청을 처리하는 ORIGIN 서버의 소프트웨어 정보
    - 응답에서 사용
- Date: 메시지가 발생한 날짜와 시간
    - 응답에서 사용
### 특별한 정보
- Host: 요청한 호스트 정보(도메인)
    - 요청에서 사용
    - 필수값임!!
- Location: 페이지 리다이렉션
- Allow: 허용가능한 HTTP 메서드
    - 405(method Not Allowed) 에서 응답에 포함해야함
    - ex. Allow: GET, HEAD, PUT
- Retry-After
    - 503: 서비스가 언제까지 불능인지 알려줄 수 있음
### 인증
- Authorization: 클라이언트 인증 정보를 서버에 전달
- WWW-Authenticate: 리소스 접근시 필요한 인증 방법 정의
    - 401 Unauthorized 응답과 함께 사용
