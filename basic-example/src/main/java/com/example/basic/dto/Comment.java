package com.example.basic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "COMMENT_SEQ_GENERATOR")
    @Column(name="seq")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    private String content;

    public Comment(String content, Post post){
        this.content = content;
        this.post = post;
    }
}
