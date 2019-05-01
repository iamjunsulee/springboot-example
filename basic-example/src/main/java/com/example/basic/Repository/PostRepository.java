package com.example.basic.Repository;

import com.example.basic.Dto.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
