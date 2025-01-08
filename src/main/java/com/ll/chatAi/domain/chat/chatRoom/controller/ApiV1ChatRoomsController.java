package com.ll.chatAi.domain.chat.chatRoom.controller;

import com.ll.chatAi.domain.chat.chatRoom.dto.RequestCreateRoom;
import com.ll.chatAi.domain.chat.chatRoom.entity.ChatRoom;
import com.ll.chatAi.domain.chat.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/chat/rooms")
@RequiredArgsConstructor
@CrossOrigin(
        origins = {"https://cdpn.io", "https://chat-app-front2501.vercel.app", "http://127.0.0.1:5173"}
)

public class ApiV1ChatRoomsController {
    private final ChatRoomService chatRoomService;

    @GetMapping()
    public List<ChatRoom> roomList(){
        List<ChatRoom> chatRooms = chatRoomService.getAll();
        return chatRooms;
    }

    @GetMapping("{roomId}")
    public ChatRoom getChatRoom(@PathVariable Long roomId){
        ChatRoom chatRoom = chatRoomService.getChatRoom(roomId);
        return chatRoom;
    }

    @PostMapping()
    public ChatRoom createRoom(@RequestBody RequestCreateRoom requestCreateRoom){
        ChatRoom chatRoom = chatRoomService.make(requestCreateRoom.getName());
        return chatRoom;
    }

}
