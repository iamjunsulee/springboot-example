package com.example.basic.controller;

import com.example.basic.dto.Post;
import com.example.basic.dto.PostDto;
import com.example.basic.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class BasicController {
    private PostService postService;

    //Controller는 RestController와 다르게 페이지를 이동시킨다.
    //index 라는 String 문자를 return하는게 아니라 "index.html"을 실행시킨다.
    @RequestMapping(value = "/home")
    public String home(Model model){
        model.addAttribute("text","Welcome Home");
        return "index";
    }

    //Controller에서 return 되는 값을 그대로 출력하기 위해서는 @ResponseBody를 이용한다.
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public @ResponseBody String getString(){
        return "Hello-world";
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("posts",postService.findAll());
        return "post";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        postService.delete(id);
        return "redirect:/";
    }
    @GetMapping("/post/{id}")
    public String view(@PathVariable Long id, Model model){
        Post post = postService.findOne(id).get();
        model.addAttribute("post",post);
        return "edit";
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @Valid Post post){
        PostDto postDto = new PostDto(post);
        postService.update(id, postDto);
        return "redirect:/";
    }
    @GetMapping("/post/new")
    public String newPost(@ModelAttribute @Valid PostDto postDto){
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute @Valid PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "post/new";
        }
        System.out.println("-------------------------------");
        System.out.println(postDto.getId());
        System.out.println(postDto.getTitle());
        System.out.println(postDto.getContent());
        System.out.println(postDto.getAuthor());
        System.out.println("-------------------------------");
        postService.save(postDto);
        return "redirect:/";
    }
}
