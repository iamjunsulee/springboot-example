package com.example.basic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime regdate;
    private List<Comment> comment;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .author(author)
                .content(content)
                .build();
    }

    public PostDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.regdate = LocalDateTime.now();
    }
}
