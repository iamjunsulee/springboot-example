package com.example.basic.repository;

import com.example.basic.dto.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.basic.dto.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> findByName(String name){
        return jpaQueryFactory.selectFrom(post)
                .where(post.author.eq(name))
                .fetch();
    }
}
