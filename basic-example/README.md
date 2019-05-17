Spring-Boot Basic Example
=========================
>SpringBoot, Thymeleaf, JPA, H2를 사용한 간단 예제

### Prerequisites
* Java 8
* SpringBoot 2.1.4
* Thymeleaf
* JPA
* H2

Structure
---------
````bash
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
 
 Comment
 -------
### @Controller와 @RestController의 차이
 >일반적인 Spring MVC 컨트롤러와 RESTful 웹 서비스 컨트롤러의 주요 차이점은 HTTP ResponseBody가 생성되는 방식이다. 
 일반적인 MVC 컨트롤러는 View 기술을 사용하지만, RESTful 웹 서비스 컨트롤러는 객체를 반환하면, 객체 데이터는 JSON / XML 형식의 HTTP 응답에 직접 작성되게 된다.
 
#### Sample Code 
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

### @WebMvcTest
* Spring MVC controller를 테스트하기 위해 사용한다.
* @Controller, @ControllerAdvice, @JsonComponent, Filter, WebMvcConfigurer, HandlerMethodArgumentResolver 설정만 로드하기 때문에 @SpringBootTest 어노테이션보다 가볍게 테스트할 수 있다.

#### Sample Code
~~~java
@RunWith(SpringRunner.class)
@WebMvcTest(BasicController.class)  
public class BasicControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;
    
    @Test
    public void modifyPost() throws Exception{
        Post post = new Post(1L,"제목","내용","작성자");
        
        given(postService.update(anyLong(),any())).willReturn(post);

        this.mvc.perform(post("/edit/{id}",1L)
                .param("title","제목")
                .param("content","내용")
                .param("author","작성자"))
                .andExpect(status().isFound())
                .andExpect(header().string(HttpHeaders.LOCATION,"/"));
    }
}
~~~
* @WebMvcTest 어노테이션을 사용하기 위해서는 특정 컨트롤러 클래스를 명시해야 한다.
* MokcMvc는 컨트롤러 테스트시 모든 의존성을 로드하는 게 아니라 어노테이션과 함께 명시된 BasicController와 관련된 bean만 로드하여 가볍게 MVC 테스트를 할 수 있다.
* given(postService.update(anyLong(),any())).willReturn(post) : update 서비스가 실행되면 위에서 생성한 가짜 객체를 리턴한다.
* 해당 객체의 응답값이 예상값과 동일한지 테스트한다.