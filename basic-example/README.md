Spring-Boot Basic Example
=========================
>SpringBoot, Thymeleaf, JPA, H2를 사용한 간단 예제
###Prerequisites
* Java 8
* SpringBoot 2.1.4
* Thymeleaf
* JPA
* H2
##Structure
````
main
├── java
│   └── com
│       └── example
│           └── basic
│               ├── BasicApplication.java
│               ├── controller
│               │   └── BasicController.java
│               │   └── RestApiController.java
│               ├── dto
│               │   └── Post.java
│               │   └── PostDto.java
│               ├── repository
│               │   └── PostRepository.java
│               └── service
│                   └── PostService.java
└── resources
    ├── application.yml
    ├── import.sql
    ├── static
    └── templates
        ├── edit.html
        ├── index.html
        └── add.html
        └── post.html
 ````
 ##Comment
 ###@Controller와 @RestController의 차이
 >일반적인 Spring MVC 컨트롤러와 RESTful 웹 서비스 컨트롤러의 주요 차이점은 HTTP ResponseBody가 생성되는 방식이다. 
 일반적인 MVC 컨트롤러는 View 기술을 사용하지만, RESTful 웹 서비스 컨트롤러는 객체를 반환하면, 객체 데이터는 JSON / XML 형식의 HTTP 응답에 직접 작성되게 된다.
 
~~~java
@RestController
public class RestController{
    @RequestMapping(value="/list")
    public String list(){
        return "list";
    }
}
~~~
~~~java
@Controller
public class Controller{
    @ReqeustMapping(value="/list")
    public @ResponseBody String list(){
        return "list";
    }
}
~~~
