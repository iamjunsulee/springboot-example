package com.example.basic.service;

import com.example.basic.dto.Post;
import com.example.basic.dto.PostDto;
import com.example.basic.dto.Search;
import com.example.basic.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;
    /*
    DB 데이터를 등록/수정/삭제하는 메소드에는 @Transactional 사용한다.
    해당 annotation은 메소드 내에서 exception 발생 시, DB 작업을 초기화시킨다.
    롤백하지는 않고, DB에 커밋을 하지 않음.
     */
    @Transactional
    public void save(PostDto dto) {
        postRepository.save(dto.toEntity());
    }

    @Transactional
    public Post update(Long id, PostDto dto){
        Optional<Post> postOptional = this.findOne(id);

        if(!postOptional.isPresent()){
            return null;
        }

        Post old = postOptional.get();
        old.setId(id);
        old.setTitle(dto.getTitle());
        old.setContent(dto.getContent());
        old.setAuthor(dto.getAuthor());
        old.setRegdate(dto.getRegdate());

        return old;
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Post> findOne(Long id) {
        return postRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Post> findByName(String name){
        return postRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByQueryDsl(Pageable pageable){
        return postRepository.findAllByQueryDsl(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Post> findByCondition(Search search, Pageable pageable){
        String name = new String();
        String title = new String();

        if(search.getValue() == 1){
            title = search.getName();
        }else{
            name = search.getName();
        }
        return postRepository.findByCondition(name,title, pageable);
    }
}
