package com.ll.chatAi.domain.article.article.dto;

import com.ll.chatAi.domain.article.article.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createDate;
    private final LocalDateTime modifiedDate;

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = article.getAuthor().getUsername();
        this.createDate = article.getCreateDate();
        this.modifiedDate = article.getModifyDate();
    }
}
