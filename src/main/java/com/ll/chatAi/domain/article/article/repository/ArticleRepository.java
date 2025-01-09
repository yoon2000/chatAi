package com.ll.chatAi.domain.article.article.repository;

import com.ll.chatAi.domain.article.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByOrderByIdDesc();
    Page<Article> findByTitleContainingOrContentContaining(String kw, String kw_, Pageable pageable);
    Page<Article> findByTitleContaining(String kw, Pageable pageable);
    Page<Article> findByContentContaining(String kw, Pageable pageable);
    Page<Article> findByAuthor_usernameContainingOrTitleContainingOrContentContaining(String kw, String kw_, String kw__, Pageable pageable);
    Page<Article> findByAuthor_usernameContaining(String kw, Pageable pageable);
    Page<Article> findByAuthor_usernameContainingOrTitleContainingOrContentContainingOrTags_contentOrComments_author_usernameContainingOrComments_bodyContaining(String kw, String kw_, String kw__, String kw___, String kw____, String kw_____, Pageable pageable);
}
