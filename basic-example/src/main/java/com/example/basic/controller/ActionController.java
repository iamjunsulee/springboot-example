package com.example.basic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {

    //RestController는 return 되는 값을 페이지에 보여준다.
    //POST 방식이나 PUT 방식을 쓰게 된다면 일반적인 브라우저에서는 볼 수 없고, RESTful 접속 도구를 설치해야 볼 수 있다.
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getHome(){
        return "index";
    }
}
