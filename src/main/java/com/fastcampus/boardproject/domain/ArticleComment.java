package com.fastcampus.boardproject.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article; // 클래스로 지정해야되는구나
    private String content;

    private LocalDateTime createAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
