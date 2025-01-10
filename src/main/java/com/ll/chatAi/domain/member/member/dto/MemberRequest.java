package com.ll.chatAi.domain.member.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
