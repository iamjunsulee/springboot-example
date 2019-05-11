package com.example.basic.controller;

import com.example.basic.dto.Post;
import com.example.basic.dto.PostDto;
import com.example.basic.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RestApiController {

    //@Autowired가 아니라 생성자를 통해서 Bean을 주입
    //모든 필드를 인자로 하는 생성자를 Lombok의 @AllArgsConstructor가 대신 생성해준다.
    private PostService postService;

    //RestController는 return 되는 값을 페이지에 보여준다.
    //POST 방식이나 PUT 방식을 쓰게 된다면 일반적인 브라우저에서는 볼 수 없고, RESTful 접속 도구를 설치해야 볼 수 있다.
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getHome(){
        return "index";
    }

    /*
    테이블과 매핑되는 Entity 클래스를 Request, Response 클래스로 사용하면 안됨.
    Entity 클래스가 변경되면 여러 클래스에 영향을 끼치게 됨.
    Entity 클래스와 Controller에서 사용하는 DTO는 분리해서 사용하면 좋음.
     */
    @PostMapping("/addPost")
    public void addPost(@RequestBody PostDto dto){
        postService.save(dto);
    }

    @PutMapping("/editPost/{id}")
    public void editPost(@PathVariable Long id, @RequestBody PostDto dto){
        postService.update(id, dto);
    }

    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable Long id){
        postService.delete(id);
    }
    
    @GetMapping("/list")
    public List<Post> list(){
        return postService.findAll();
    }
}
