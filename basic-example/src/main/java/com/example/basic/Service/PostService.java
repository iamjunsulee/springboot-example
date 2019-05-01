package com.example.basic.Service;

import com.example.basic.Dto.Post;
import com.example.basic.Dto.PostDto;
import com.example.basic.Repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private PostRepository postRepository;

    /*
    DB 데이터를 등록/수정/삭제하는 메소드에는 @Transactional 사용한다.
    해당 annotation은 메소드 내에서 exception 발생 시, DB 작업을 초기화시킨다.
    롤백하지는 않고, DB에 커밋을 하지 않음.
     */
    @Transactional
    public Long save(PostDto dto) {
        return postRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<Post> findAll(){
        return postRepository.findAll();
    }


}