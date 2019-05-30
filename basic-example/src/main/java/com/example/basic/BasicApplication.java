package com.example.basic;

import com.example.basic.dto.Post;
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

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        for(int i = 1;i <= 120;i++) {
            if(i % 2 == 0){
                postRepository.save(new Post("제목"+i,"내용"+i,"이준수"));
            }else {
                postRepository.save(new Post("제목"+i,"내용"+i,"삼준수"));
            }
        }
    }
}
