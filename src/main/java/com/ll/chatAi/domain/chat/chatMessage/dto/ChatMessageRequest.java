package com.ll.chatAi.domain.chat.chatMessage.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChatMessageRequest {
    private String content;
    private String author;
}

