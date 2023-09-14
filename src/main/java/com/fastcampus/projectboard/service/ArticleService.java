package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleWithCommentsDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service /// 스테레오타입 에노테이션으로 Service를 붙인다.
public class ArticleService {
    private final ArticleRepository articleRepository;
    // 댓글 레포를 넣으면 좋겠지만 댓글은 게시글에서 다루면 좋으니 여기서 의존성을 넣진 않겠다.

    @Transactional(readOnly = true) //// 레포가 아니라 service에서 트랜잭션을 거네?
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        return Page.empty();
    }
    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {
    }

    public void updateArticle(ArticleDto dto){

    }

    public void deleteArticle(long articleId) {
    }
}
