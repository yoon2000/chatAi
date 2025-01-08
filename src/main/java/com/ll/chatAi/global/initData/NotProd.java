package com.ll.chatAi.global.initData;

import com.ll.chatAi.domain.article.article.entity.Article;
import com.ll.chatAi.domain.article.article.service.ArticleService;
import com.ll.chatAi.domain.chat.chatMessage.service.ChatMessageService;
import com.ll.chatAi.domain.chat.chatRoom.entity.ChatRoom;
import com.ll.chatAi.domain.chat.chatRoom.service.ChatRoomService;
import com.ll.chatAi.domain.member.member.entity.Member;
import com.ll.chatAi.domain.member.member.service.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(ChatRoomService chatRoomService, ChatMessageService chatMessageService,
                                         MemberService memberService, ArticleService articleService) {
        return args -> {
            ChatRoom chatRoom1 = chatRoomService.make("공부");
            ChatRoom chatRoom2 = chatRoomService.make("식사");
            ChatRoom chatRoom3 = chatRoomService.make("휴식");

            IntStream.rangeClosed(1, 100).forEach(num -> {
                chatMessageService.write(chatRoom1, "홍길동", "공부 메시지" + num);
            });

            Member member1 = memberService.join("user1", "1234").getData();
            Member member2 = memberService.join("user2", "1234").getData();
            Member member3 = memberService.join("user3", "1234").getData();

            Article article1 = articleService.write(member1.getId(), "제목1", "내용1").getData();
            Article article2 = articleService.write(member2.getId(), "제목2", "내용2").getData();

            Article article3 = articleService.write(member3.getId(), "제목3", "내용3").getData();
            Article article4 = articleService.write(member3.getId(), "제목4", "내용4").getData();

        };
    }
}