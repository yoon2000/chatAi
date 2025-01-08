package com.ll.chatAi.domain.chat.chatMessage.controller;

import com.ll.chatAi.domain.chat.chatMessage.dto.ChatMessageRequest;
import com.ll.chatAi.domain.chat.chatMessage.entity.ChatMessage;
import com.ll.chatAi.domain.chat.chatMessage.service.ChatMessageService;
import com.ll.chatAi.domain.chat.chatRoom.entity.ChatRoom;
import com.ll.chatAi.domain.chat.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"https://cdpn.io", "https://chat-app-front2501.vercel.app", "http://127.0.0.1:5173"}
)
public class ApiV1ChatMessageController {
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/api/v1/chat/rooms/{roomId}/messages")
    public List<ChatMessage> getChatMessages(@PathVariable Long roomId, @RequestParam Long afterChatMessageId){

        List<ChatMessage> chatMessages = chatMessageService.getChatMessages(roomId, afterChatMessageId);

        return chatMessages;
    }


    @PostMapping("/api/v1/chat/rooms/{roomId}/messages")
    public ResponseEntity<String> createChatMessage(@PathVariable Long roomId, @RequestBody ChatMessageRequest chatMessageRequest) {
        try {
            ChatRoom chatRoom = chatRoomService.getChatRoom(roomId);
            chatMessageService.write(chatRoom, chatMessageRequest.getAuthor(), chatMessageRequest.getContent());
            return ResponseEntity.ok("메시지 작성 완료");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메시지 작성 실패: " + e.getMessage());
        }
    }
}
