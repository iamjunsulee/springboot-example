package com.example.basic.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    String content;

    String author;

    /*
    생성자와 빌더는 생성시점에 값을 채워주는 역할은 똑같지만,
    생성자의 경우, 값이 다르게 들어간 경우, 실제로 코드를 실행하기전까진 전혀 문제를 찾을 수 없다.
    반면에 빌더를 사용하게 되면 어느 필드에 어떤 값을 채워야 할지 명확하게 인지할 수 있다.
     */
    @Builder
    public  Post(String content, String author){
        this.content = content;
        this.author = author;
    }
}
