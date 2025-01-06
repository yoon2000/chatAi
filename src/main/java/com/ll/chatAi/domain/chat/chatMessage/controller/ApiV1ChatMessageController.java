package com.ll.chatAi.domain.chat.chatMessage.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ApiV1ChatMessageController {

    @GetMapping("/api/v1/chat/rooms/{roomId}/messages")
    public String getChatMessages(@PathVariable Long roomId, @RequestParam("afterChatMessageId") long afterChatMessageId){
        return roomId + "번채팅방 메시지 목록 조회 완료 id : " + afterChatMessageId;
    }

    @PostMapping("/api/v1/chat/rooms/{roomId}/messages")
    public String createChatMessage(@PathVariable Long roomId){
        return roomId + "번채팅방 메시지 생성 완료";
    }
}
