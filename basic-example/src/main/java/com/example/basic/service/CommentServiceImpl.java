package com.example.basic.service;

import com.example.basic.dto.Comment;
import com.example.basic.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;

    public void save(Comment comment){
        commentRepository.save(comment);
    }
}
