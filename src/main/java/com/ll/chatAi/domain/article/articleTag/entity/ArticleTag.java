package com.ll.chatAi.domain.article.articleTag.entity;

import com.ll.chatAi.domain.article.article.entity.Article;
import com.ll.chatAi.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
//@AllArgsConstructor
//@NoArgsConstructor 기본생성자
@SuperBuilder
@ToString(callSuper = true)
public class ArticleTag extends BaseEntity {
    public ArticleTag(){

    }
    private String content;
    @ManyToOne(fetch =  FetchType.LAZY)
    private Article article;
}
