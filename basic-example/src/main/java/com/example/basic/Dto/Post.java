package com.example.basic.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SequenceGenerator(name="POST_SEQ_GENERATOR", sequenceName = "POST_SEQ", initialValue = 1, allocationSize = 1)
// name=식별자 생성기 이름, sequenceName=DB에 등록될 시퀀스이름, initialValue=최초시작하는 수, allocationSize=증가하는수)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "POST_SEQ_GENERATOR")
    private long id;

    String title;
    @Column(columnDefinition = "TEXT")
    String content;
    String author;
    LocalDateTime regdate;
    /*
    생성자와 빌더는 생성시점에 값을 채워주는 역할은 똑같지만,
    생성자의 경우, 값이 다르게 들어간 경우, 실제로 코드를 실행하기전까진 전혀 문제를 찾을 수 없다.
    반면에 빌더를 사용하게 되면 어느 필드에 어떤 값을 채워야 할지 명확하게 인지할 수 있다.
     */
    @Builder
    public  Post(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
        this.regdate = LocalDateTime.now();
    }
}
