package com.ll.chatAi.domain.article.article.controller;

import com.ll.chatAi.domain.article.article.dto.ArticleDto;
import com.ll.chatAi.domain.article.article.dto.ArticleModifyRequest;
import com.ll.chatAi.domain.article.article.dto.ArticleWriteRequest;
import com.ll.chatAi.domain.article.article.entity.Article;
import com.ll.chatAi.domain.article.article.service.ArticleService;
import com.ll.chatAi.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public RsData<ArticleDto> writeArticle(@Valid @RequestBody ArticleWriteRequest articleWriteRequest) {
        Article article = articleService.write(articleWriteRequest.getTitle(), articleWriteRequest.getContent());
        return new RsData(
                "200",
                "게시글 작성 완료",
                new ArticleDto(article)
        );
    }

    @PatchMapping("/{id}")
    public RsData<ArticleDto> updateArticle(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        Article article = articleService.getArticle(id);
        Article modifiedArticle = articleService.modify(article, articleModifyRequest.getTitle(), articleModifyRequest.getContent());
        return new RsData(
                "200",
                "게시글 수정 완료",
                new ArticleDto(modifiedArticle)
        );
    }

    @DeleteMapping("/{id}")
    public RsData<Void> deleteArticle(@PathVariable("id") Long id) {
        System.out.println(id);
        articleService.delete(id);


        return new RsData(
                "200",
                "게시글 삭제 완료"
        );
    }
}
