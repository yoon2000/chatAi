package com.ll.chatAi.domain.chat.chatMessage.repository;

import com.ll.chatAi.domain.chat.chatMessage.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    public List<ChatMessage> findByChatRoomIdAndIdGreaterThan(Long roomId, Long afterChatMessageId);
}
