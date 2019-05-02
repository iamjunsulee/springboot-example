package com.example.basic.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String content;
    private String author;

    public Post toEntity(){
        return Post.builder()
                .author(author)
                .content(content)
                .build();
    }
}
