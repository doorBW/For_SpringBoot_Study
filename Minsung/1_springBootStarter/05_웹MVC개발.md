# 회원 관리 예제 - 웹 MVC 개발

## 홈 화면 추가

``` html
<!-- templates/home.html -->

<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<body>
    <a href="/members/new">회원가입</a>
    <a href="/members">회원목록</a>
</body>
</html>
```

``` java
// hello/hellospring/controller/HomeController.java

package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
```

> 컨트롤러가 정적파일보다 우선순위가 높습니다.



## 등록

## 조회

등록과 조회는 위와 유사하여 생략하였습니다.