package com.example.basic.repository;

import com.example.basic.dto.Post;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import static com.example.basic.dto.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> findByName(String name) {
        return jpaQueryFactory.selectFrom(post)
                .where(post.author.eq(name))
                .fetch();
    }

    @Override
    public Page<Post> findAllByQueryDsl(Pageable pageable) {
        QueryResults<Post> results = jpaQueryFactory.selectFrom(post)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<Post> findByCondition(String name, String title, Pageable pageable) {
        QueryResults<Post> results
                = jpaQueryFactory.selectFrom(post)
                .where(eqAuthor(name), eqTitle(title))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private BooleanExpression eqTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return post.title.eq(title);
    }

    private BooleanExpression eqAuthor(String name) {
        if(StringUtils.isEmpty(name)){
            return null;
        }
        return post.author.eq(name);
    }
}
