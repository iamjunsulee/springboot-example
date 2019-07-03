package com.example.basic;

import com.example.basic.dto.Comment;
import com.example.basic.dto.Post;
import com.example.basic.repository.CommentRepository;
import com.example.basic.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class BasicApplication implements ApplicationRunner {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        for(int i = 1;i <= 120;i++) {
            if(i % 2 == 0){
                Post post = new Post("제목"+i,"내용"+i,"이준수");
                postRepository.save(post);
                commentRepository.save(new Comment("댓글입니다."+i,post));
            }else {
                Post post = new Post("제목"+i,"내용"+i,"삼준수");
                postRepository.save(post);
                commentRepository.save(new Comment("댓글입니다."+i,post));
            }
        }
    }
}
