package com.ll.chatAi.domain.article.article.entity;

import com.ll.chatAi.domain.member.member.entity.Member;
import com.ll.chatAi.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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

    //add comment
    public static class Comment {
        private Member author;
        private String content;
    }

    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
