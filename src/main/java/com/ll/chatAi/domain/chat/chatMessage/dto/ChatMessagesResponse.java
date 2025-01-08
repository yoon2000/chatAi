package com.ll.chatAi.domain.chat.chatMessage.dto;

import com.ll.chatAi.domain.chat.chatMessage.entity.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ChatMessagesResponse {
    List<ChatMessage> chatMessages;
    private int totalCount;
}
