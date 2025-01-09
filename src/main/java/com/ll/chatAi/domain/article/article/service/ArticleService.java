package com.ll.chatAi.domain.article.article.service;

import com.ll.chatAi.domain.article.article.entity.Article;
import com.ll.chatAi.domain.article.article.repository.ArticleRepository;
import com.ll.chatAi.domain.article.articleComment.entity.ArticleComment;
import com.ll.chatAi.domain.article.articleComment.repository.ArticleCommentRepository;
import com.ll.chatAi.domain.member.member.service.MemberService;
import com.ll.chatAi.global.rsData.RsData;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberService memberService;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional
    public RsData<Article> write(long memberId, String title, String content) {
        Article article = Article.builder()
                .author(memberService.getMember(memberId))
                .title(title)
                .content(content)
                .build();

        articleRepository.save(article);

        return RsData.of("200", "%d번 게시글이 완료되었습니다".formatted(article.getAuthor().getId()), article);
    }

    public Article getArticle(long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    @Transactional
    public void modify(Article article, String title, String content){
        article.setTitle(title);
        article.setContent(content);

//        return articleRepository.save(article); // 이걸 안해도 영속성 컨테스트가 저장해줌 BUT Transactional 붙여야함.
    }

    public void modifyComment(ArticleComment comment, String body) {
        comment.setBody(body);
    }
}
