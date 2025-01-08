package com.ll.chatAi.domain.article.articleComment.entity;

import com.ll.chatAi.domain.article.article.entity.Article;
import com.ll.chatAi.domain.member.member.entity.Member;
import com.ll.chatAi.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ArticleComment extends BaseEntity {
    @ManyToOne
    private Article article;

    @ManyToOne
    private Member author;

    private String body;
}
