package com.example.basic.repository;

import com.example.basic.dto.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> findByName(String name);
}
