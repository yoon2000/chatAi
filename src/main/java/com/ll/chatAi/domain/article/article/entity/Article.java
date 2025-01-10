package com.ll.chatAi.domain.article.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.chatAi.domain.article.articleComment.entity.ArticleComment;
import com.ll.chatAi.domain.article.articleTag.entity.ArticleTag;
import com.ll.chatAi.domain.member.member.entity.Member;
import com.ll.chatAi.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @ManyToOne(fetch =  FetchType.LAZY)
    private Member author;

    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = ALL, orphanRemoval = true) //Lazy 모드로 가볍게 천천히 불러옴!
    @Builder.Default // 왜 넣음? -> 기본값을 넣어주기 위해서
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

    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = ALL, orphanRemoval = true)
    @Builder.Default // 왜 넣음? -> 기본값을 넣어주기 위해서
    @ToString.Exclude
    private List<ArticleTag> tags = new ArrayList<>();

    public void addTag(String content) {
        ArticleTag articleTag = ArticleTag.builder()
                .article(this)
                .content(content)
                .build();
        tags.add(articleTag);
    }

    public void addTags(String... contents) {
        for (String content : contents) {
            addTag(content);
        }
    }

    public String getTagsStr() {

        String tagsStr = tags
                .stream()
                .map(ArticleTag::getContent)
                .collect(Collectors.joining(" #"));

        if(tagsStr.isBlank()){
            return "";
        }

        return "#" + tagsStr;
    }
}
