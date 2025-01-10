package com.ll.chatAi.domain.member.member.controller;

import com.ll.chatAi.domain.member.member.dto.MemberRequest;
import com.ll.chatAi.domain.member.member.entity.Member;
import com.ll.chatAi.domain.member.member.service.MemberService;
import com.ll.chatAi.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public RsData<String> signup(@Valid @RequestBody MemberRequest memberRequest) {
        Member member = memberService.join(memberRequest.getUsername(),memberRequest.getPassword());

        return RsData.of("200", "회원가입 성공", member.getUsername());
    }

    @PostMapping("/login")
    public void login() {
        System.out.println("login");
    }

    @GetMapping("/logout")
    public void logout() {
        System.out.println("logout");
    }

    @GetMapping("/me")
    public void me() {
        System.out.println("me");
    }
}