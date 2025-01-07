package com.ll.chatAi.domain.chat.chatMessage.controller;

import com.ll.chatAi.domain.chat.chatMessage.entity.ChatMessage;
import com.ll.chatAi.domain.chat.chatMessage.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(
        origins = "https://cdpn.io"
)
public class ApiV1ChatMessageController {
    private final ChatMessageService chatMessageService;

    @GetMapping("/api/v1/chat/rooms/{roomId}/messages")
    public List<ChatMessage> getChatMessages(@PathVariable Long roomId, @RequestParam long afterChatMessageId){

        List<ChatMessage> chatMessages = chatMessageService.getChatMessages(roomId, afterChatMessageId);

        return chatMessages;
    }

    @PostMapping("/api/v1/chat/rooms/{roomId}/messages")
    public String createChatMessage(@PathVariable Long roomId){
        return roomId + "번채팅방 메시지 생성 완료";
    }
}
