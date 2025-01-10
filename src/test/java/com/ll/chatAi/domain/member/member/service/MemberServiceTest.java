package com.ll.chatAi.domain.member.member.service;

import com.ll.chatAi.domain.member.member.entity.Member;
import com.ll.chatAi.global.rsData.RsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @DisplayName("회원가입")
    @Test
    void t1() {
        RsData<Member> joinRs = memberService.join("usernew", "1234");
        Member member = joinRs.getData();
        assertThat(member.getId()).isGreaterThan(0L);
    }
}