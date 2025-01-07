package com.ll.chatAi.domain.chat.chatMessage.service;

import com.ll.chatAi.domain.chat.chatMessage.entity.ChatMessage;
import com.ll.chatAi.domain.chat.chatMessage.repository.ChatMessageRepository;
import com.ll.chatAi.domain.chat.chatRoom.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public void write(ChatRoom chatRoom, String writerName, String content) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .writerName(writerName)
                .content(content)
                .build();

        chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getChatMessages(Long roomId, Long afterChatMessageId) {
        return chatMessageRepository.findByChatRoomIdAndIdGreaterThan(roomId, afterChatMessageId);
    }
}
