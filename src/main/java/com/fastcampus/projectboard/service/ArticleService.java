package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleWithCommentsDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional
@Service /// 스테레오타입 에노테이션으로 Service를 붙인다.
public class ArticleService {
    private final ArticleRepository articleRepository;
    // 댓글 레포를 넣으면 좋겠지만 댓글은 게시글에서 다루면 좋으니 여기서 의존성을 넣진 않겠다.

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if(searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch(searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag(searchKeyword, pageable).map(ArticleDto::from);
        };
    }
    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(ArticleDto dto){
        try {
            var article = articleRepository.getReferenceById(dto.id());
            if(dto.title() != null) { article.setTitle(dto.title()); }
            if(dto.content() != null) { article.setContent(dto.content());}
            article.setHashtag(dto.hashtag());
        } catch(EntityNotFoundException e){
            System.out.println("ArticleService.updateArticle has error");
        }
    }

    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }
}
