package com.example.basic.controller;

import com.example.basic.dto.Post;
import com.example.basic.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BasicController.class)  //controller를 테스트하기 위한 annotation
public class BasicControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;

    @Test
    public void newPost() throws Exception{
        this.mvc.perform(get("/post/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void modifyPost() throws Exception{
        Post post = new Post(1L,"제목","내용","작성자");
        //postService.update를 호출하면, new Post(1L,"제목","내용","작성자")를 받아 올거라는 가짜로 만든 객체
        given(postService.update(anyLong(),any())).willReturn(post);

        this.mvc.perform(post("/edit/{id}",1L)
                .param("title","제목")
                .param("content","내용")
                .param("author","작성자"))
                .andExpect(status().isFound())
                .andExpect(header().string(HttpHeaders.LOCATION,"/"));
    }

    @Test
    public void deletePost() throws Exception{
        doNothing().when(postService).delete(anyLong());
        this.mvc.perform(get("/delete/{id}",1L))
                .andExpect(status().isFound())
                .andExpect(header().string(HttpHeaders.LOCATION,"/"));
    }
}
