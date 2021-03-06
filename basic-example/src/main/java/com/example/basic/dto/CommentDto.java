package com.example.basic.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    @NotNull
    private Long postId;

    @NotBlank
    private String content;
}
