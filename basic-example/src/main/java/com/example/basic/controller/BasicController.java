package com.example.basic.controller;

import com.example.basic.dto.Post;
import com.example.basic.dto.PostDto;
import com.example.basic.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/post")
public class BasicController {
    private PostService postService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("posts",postService.findAll());
        return "posts";
    }
    /*
        Pageable
        - page : 페이지 번호(0부터 시작)
        - sort : 정렬방식
        - size : 한 페이지 개수
        @PageableDefault : 기본 페이지 크기 및 정렬 방식 설정
     */
    @GetMapping("/pages")
    public String findAllByPageable(Model model, @PageableDefault(sort = {"id"},direction = Sort.Direction.ASC,size = 3) Pageable pageable){
        Page<Post> postPage = postService.findAll(pageable);
        model.addAttribute("posts",postPage);
        return "posts";
    }

    @GetMapping("/pagesbyquerydsl")
    public String findAllByQueryDsl(Model model, @PageableDefault(sort = {"id"},direction = Sort.Direction.ASC,size = 10) Pageable pageable){
        model.addAttribute("posts",postService.findAllByQueryDsl(pageable));
        return "posts";
    }

    @GetMapping("/deletePost/{id}")
    public String delete(@PathVariable Long id){
        postService.delete(id);
        return "redirect:/post/pagesbyquerydsl";
    }
    @GetMapping("/viewPost/{id}")
    public String findById(@PathVariable Long id, Model model){
        Post post = postService.findOne(id).get();
        model.addAttribute("post",post);
        return "edit";
    }

    @PostMapping("/modifyPost/{id}")
    public String update(@PathVariable Long id, @Valid Post post){
        PostDto postDto = new PostDto(post);
        postService.update(id, postDto);
        return "redirect:/post/pagesbyquerydsl";
    }
    @GetMapping("/newPost")
    public String newPost(@ModelAttribute @Valid PostDto postDto){
        return "add";
    }

    @PostMapping("/addPost")
    public String insert(@ModelAttribute @Valid PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "newPost";
        }
        postService.save(postDto);
        return "redirect:/post/pagesbyquerydsl";
    }
}
