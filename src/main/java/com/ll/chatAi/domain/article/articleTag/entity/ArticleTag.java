package com.ll.chatAi.domain.article.articleTag.entity;

import com.ll.chatAi.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ArticleTag extends BaseEntity {

}
