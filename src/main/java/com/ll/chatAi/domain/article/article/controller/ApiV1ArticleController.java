package com.ll.chatAi.domain.article.article.controller;

import com.ll.chatAi.domain.article.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public void getArticles() {
        articleService.findAll();
    }

    @GetMapping("id")
    public void getArticle(@PathVariable("id") Long id) {
        articleService.getArticle(id);
    }

    @PostMapping
    public void writeArticle(@RequestBody String title, @RequestBody String content) {
    }

    @PostMapping
    public void updateArticle(@PathVariable("id") Long id, @RequestBody String title, @RequestBody String content) {
    }

    @DeleteMapping
    public void deleteArticle(@PathVariable("id") Long id) {
    }
}
