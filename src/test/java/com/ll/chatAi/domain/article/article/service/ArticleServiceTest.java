package com.ll.chatAi.domain.article.article.service;

import com.ll.chatAi.domain.article.article.entity.Article;
import com.ll.chatAi.domain.article.articleComment.entity.ArticleComment;
import com.ll.chatAi.domain.member.member.entity.Member;
import com.ll.chatAi.domain.member.member.service.MemberService;
import com.ll.chatAi.global.Ut;
import com.ll.chatAi.global.rsData.RsData;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private MemberService memberService;

    @DisplayName("글 쓰기")
    @Test
    void t1() {
        RsData<Article> writeRs = articleService.write(1, "제목", "내용");
        Article article = writeRs.getData();

        assertThat(article.getId()).isGreaterThan(0L);
    }

    @DisplayName("1번 글을 가져온다.")
    @Test
    void t2() {
        Article article = articleService.getArticle(1L);
        assertThat(article.getTitle()).isEqualTo("제목1");
    }

    @DisplayName("1번 글의 작성자의 username 은 user1 이다.")
    @Test
    void t3() {
        Article article = articleService.getArticle(1L);
        Member author = article.getAuthor();

        assertThat(author.getUsername()).isEqualTo("user1");
    }

    @DisplayName("1번 글의 제목을 수정한다.")
    @Test
    void t4() {
        Article article = articleService.getArticle(1L);

        Ut.thread.sleep(1000);

        articleService.modify(article, "수정된 제목", "수정된 내용");

        Article article_ = articleService.getArticle(1L);

        assertThat(article_.getTitle()).isEqualTo("수정된 제목");
    }

    @DisplayName("2번 글에 댓글들을 추가한다.")
    @Test
    @Rollback(false)
    void t5() {
        Member member1 = memberService.getMember(1L);
        Article article2 = articleService.getArticle(2L);

        article2.addComment(member1, "댓글 입니다.");
    }

    @DisplayName("1번 글의 댓글들을 수정한다.")
    @Test
    void t6() {
        Article article = articleService.getArticle(2L);

        article.getComments().forEach(comment -> {
            articleService.modifyComment(comment, comment.getBody() + "!!");
        });
    }

    @DisplayName("1번 글의 댓글 중 마지막 것을 삭제한다.")
    @Test
    void t7() {
        Article article = articleService.getArticle(2L);
        article.addComment(memberService.getMember(1L), "댓글 입니다.");

        ArticleComment lastComment = article.getComments().getLast();

        article.removeComment(lastComment);
    }

//    @DisplayName("1번 게시물의 태그(String)를 반환한다.")
//    @Test
//    void t9() {
//        Article article1 = articleService.getArticle(1L);
//
//        String tagsStr = article1.getTagsStr();
//
//        assertThat(tagsStr).isEqualTo("#자바 #백엔드");
//    }
//
//    @DisplayName("1번 게시물 toString")
//    @Test
//    void t10() {
//        Article article1 = articleService.getArticle(1L);
//
//        System.out.println(article1);
//    }
//
//    @DisplayName("1번 회원이 작성한 댓글들")
//    @Test
//    void t11() {
//        List<ArticleComment> articleComments = articleCommentService.findByAuthorId(1L);
//
//        assertThat(articleComments.size()).isGreaterThan(0);
//    }
//
//    @DisplayName("1번 회원이 작성한 태그들")
//    @Test
//    void t12() {
//        List<ArticleTag> articleTags = articleTagService.findByAuthorId(1L);
//
//        assertThat(articleTags.size()).isGreaterThan(0);
//    }
//
//    @DisplayName("아이디가 user1 인 회원이 작성한 태그들")
//    @Test
//    void t13() {
//        List<ArticleTag> articleTags = articleTagService.findByAuthorUsername("user1");
//
//        assertThat(articleTags.size()).isGreaterThan(0);
//    }
}
