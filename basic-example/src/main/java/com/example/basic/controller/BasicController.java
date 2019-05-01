package com.example.basic.controller;

import com.example.basic.Service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("list",postService.findAll());
        return "post";
    }
}
