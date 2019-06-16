package com.example.basic.repository;

import com.example.basic.dto.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> findByName(String name);
    Page<Post> findAllByQueryDsl(Pageable pageable);
    Page<Post> findByCondition(String name, String title, Pageable pageable);
}
