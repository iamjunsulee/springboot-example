package com.example.basic.service;

import com.example.basic.dto.Post;
import com.example.basic.dto.PostDto;
import com.example.basic.dto.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



public interface PostService {

    @Transactional
    void save(PostDto dto);

    @Transactional
    Post update(Long id, PostDto dto);

    @Transactional
    void delete(Long id) ;

    @Transactional(readOnly = true)
    List<Post> findAll();

    @Transactional(readOnly = true)
    Optional<Post> findOne(Long id);

    @Transactional(readOnly = true)
    List<Post> findByName(String name);

    @Transactional(readOnly = true)
    Page<Post> findAll(Pageable pageable);

    @Transactional(readOnly = true)
    Page<Post> findAllByQueryDsl(Pageable pageable);

    @Transactional(readOnly = true)
    Page<Post> findByCondition(Search search, Pageable pageable);
}