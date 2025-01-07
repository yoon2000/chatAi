package com.ll.chatAi.domain.chat.chatMessage.dto;

import lombok.Getter;

@Getter
public class ChatMessageRequest {
    private Long roomId;
    private Long afterChatMessageId;
}

