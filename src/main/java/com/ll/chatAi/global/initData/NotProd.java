package com.ll.chatAi.global.initData;

import com.ll.chatAi.domain.article.article.entity.Article;
import com.ll.chatAi.domain.article.article.repository.ArticleRepository;
import com.ll.chatAi.domain.article.article.service.ArticleService;
import com.ll.chatAi.domain.chat.chatMessage.service.ChatMessageService;
import com.ll.chatAi.domain.chat.chatRoom.entity.ChatRoom;
import com.ll.chatAi.domain.chat.chatRoom.service.ChatRoomService;
import com.ll.chatAi.domain.member.member.entity.Member;
import com.ll.chatAi.domain.member.member.service.MemberService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(
            ChatRoomService chatRoomService,
            ChatMessageService chatMessageService,
            MemberService memberService,
            ArticleService articleService,
            ArticleRepository articleRepository
    ) {
        return new ApplicationRunner() {
            @Override
            @Transactional
            public void run(ApplicationArguments args) throws Exception {
                ChatRoom chatRoom1 = chatRoomService.make("공부");
                ChatRoom chatRoom2 = chatRoomService.make("식사");
                ChatRoom chatRoom3 = chatRoomService.make("휴식");

                IntStream.rangeClosed(1, 10).forEach(num -> {
                    chatMessageService.write(chatRoom1, "홍길동", "공부 메시지" + num);
                });

                Member member1 = memberService.join("user1", "1234").getData();
                Member member2 = memberService.join("user2", "1234").getData();
                Member member3 = memberService.join("user3", "1234").getData();

                Article article1 = articleService.write("제목1", "내용1");
                Article article2 = articleService.write("제목2", "내용2");

                Article article3 = articleService.write("제목3", "내용3");
                Article article4 = articleService.write( "제목4", "내용4");

                article1.addComment(member1, "댓글1");
                article1.addComment(member1, "댓글2");

                article2.addComment(member1, "댓글3");
                article2.addComment(member1, "댓글4");
                article2.addComment(member1, "댓글5");

                article3.addComment(member1, "댓글5");
                article3.addComment(member1, "댓글6");
                article3.addComment(member1, "댓글7");
                article3.addComment(member1, "댓글8");
                article3.addComment(member1, "댓글9");
                article3.addComment(member1, "댓글10");
                article3.addComment(member1, "댓글11");
                article3.addComment(member1, "댓글12");

                article1.addTag("자바");
                article1.addTag("백엔드");
                article2.addTags("프레임워크", "스프링부트");
                article4.addTags("자바", "스프링부트");
            }
        };
    }
}