package com.ll.chatAi.domain.article.article.controller;

import com.ll.chatAi.domain.article.article.dto.ArticleDto;
import com.ll.chatAi.domain.article.article.entity.Article;
import com.ll.chatAi.domain.article.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<ArticleDto> getArticles() {
        List<Article> articles = articleService.findAll();
        List<ArticleDto> articleDtoList = articles.stream()
                .map(ArticleDto::new)
                .toList();

        return articleDtoList;
    }

    @GetMapping("{id}")
    public ArticleDto getArticle(@PathVariable("id") Long id) {
        Article article = articleService.getArticle(id);
        return new ArticleDto(article);
    }

    @PostMapping
    public void writeArticle(@RequestBody Article article) {
        articleService.write(article.getId(), article.getTitle(), article.getContent());
    }

    @PatchMapping
    public void updateArticle(@PathVariable("id") Long id, @RequestBody Article article) {
        articleService.modify(article, article.getTitle(), article.getContent());
    }

    @DeleteMapping
    public void deleteArticle(@PathVariable("id") Long id) {
        articleService.delete(id);
    }
}
