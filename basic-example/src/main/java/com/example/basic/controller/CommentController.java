package com.example.basic.controller;

import com.example.basic.dto.Comment;
import com.example.basic.dto.CommentDto;
import com.example.basic.dto.Post;
import com.example.basic.service.CommentService;
import com.example.basic.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;
    private PostService postService;

    //에러발생 시, post view를 return 하는데, 이 때 post라는 object가 없기 때문에 만들어줌.
    @ModelAttribute
    public Post post(@ModelAttribute CommentDto commentDto){
        return postService.findOne(commentDto.getPostId()).get();
    }

    @PostMapping("/addComment")
    public String insertComment(@ModelAttribute @Valid CommentDto commentDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "post";
        }
        commentService.save(new Comment(commentDto.getContent(), postService.findOne(commentDto.getPostId()).get()));
        return "redirect:/post/viewPost/"+commentDto.getPostId();
    }

    @GetMapping("/deleteComment/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/post/viewPost/"+postId;
    }
}
