package com.fastcampus.boardproject.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest // 슬라이스 테스트, controller 외의 것은 읽지 않는다. datarest의 autoconfig를 읽지 않는다. /// 05.API 테스트 정의 15:00
@DisplayName("Data REST api 테스트")
@Transactional // @Transactional을 붙이면 기본동작이 rollback이라 테스트함수가 완료되면 모두 롤백된다.
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest { /// 05.API 테스트 정의 10:00
    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    // ⭐️인테그레이션 테스트입니다. 리포지토리까지 실행시켜서 hibernate 쿼리까지 실행한다.
    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticles_thenReturnArticlesJsonResponse() throws Exception {
        // given

        // when & then
        mvc.perform(get("/api/articles")) /// 05.API 테스트 정의 12:00 static에 관한 이야기
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
                // .andDo(print());

    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticle_thenReturnArticleJsonResponse() throws Exception {
        // given

        // when & then
        mvc.perform(get("/api/articles/1")) /// 05.API 테스트 정의 12:00 static에 관한 이야기
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        // .andDo(print());

    }

    @DisplayName("[api] 게시글 -> 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleCommentsFromArticle_thenReturnArticleCommentsJsonResponse() throws Exception {
        // given

        // when & then
        mvc.perform(get("/api/articles/1/articleComments")) /// 05.API 테스트 정의 12:00 static에 관한 이야기
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        // .andDo(print());

    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleComments_thenReturnArticleCommentsJsonResponse() throws Exception {
        // given

        // when & then
        mvc.perform(get("/api/articleComments")) /// 05.API 테스트 정의 12:00 static에 관한 이야기
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        // .andDo(print());

    }
    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticleComment_thenReturnArticleCommentJsonResponse() throws Exception {
        // given

        // when & then
        mvc.perform(get("/api/articleComments/1")) /// 05.API 테스트 정의 12:00 static에 관한 이야기
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
        // .andDo(print());

    }
}
