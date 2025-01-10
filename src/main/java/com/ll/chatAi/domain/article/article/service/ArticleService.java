package com.ll.chatAi.domain.article.article.service;

import com.ll.chatAi.domain.article.article.entity.Article;
import com.ll.chatAi.domain.article.article.repository.ArticleRepository;
import com.ll.chatAi.domain.article.articleComment.entity.ArticleComment;
import com.ll.chatAi.domain.article.articleComment.repository.ArticleCommentRepository;
import com.ll.chatAi.domain.member.member.entity.Member;
import com.ll.chatAi.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberService memberService;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional
    public Article write(String title, String content) {
        Article article = Article.builder()
                .author(Member.builder().id(1L).build())
                .title(title)
                .content(content)
                .build();


        return articleRepository.save(article);
    }

    public Article getArticle(long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    @Transactional
    public Article modify(Article article, String title, String content){
        article.setTitle(title);
        article.setContent(content);

        return article; // 이걸 안해도 영속성 컨테스트가 저장해줌 BUT Transactional 붙여야함.
    }

    @Transactional
    public void modifyComment(ArticleComment comment, String body) {
        comment.setBody(body);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
