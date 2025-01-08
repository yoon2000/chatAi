package com.ll.chatAi.domain.article.article.entity;

import com.ll.chatAi.domain.article.articleComment.entity.ArticleComment;
import com.ll.chatAi.domain.member.member.entity.Member;
import com.ll.chatAi.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {

    private String title;
    private String content;
    @ManyToOne
    private Member author;

    @OneToMany(mappedBy = "article", cascade = ALL)
    @Builder.Default
    @ToString.Exclude
    private List<ArticleComment> comments = new ArrayList<>();

    public void addComment(Member memberAuthor, String body) {
        ArticleComment articleComment = ArticleComment.builder()
                .article(this)
                .author(memberAuthor)
                .body(body)
                .build();
        comments.add(articleComment);
    }


    public void removeComment(ArticleComment comment) {
        comments.remove(comment);
    }
}
