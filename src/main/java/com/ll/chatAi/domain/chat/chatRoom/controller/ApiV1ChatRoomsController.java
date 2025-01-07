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
        origins = "https://cdpn.io"
)
public class ApiV1ChatRoomsController {
    private final ChatRoomService chatRoomService;

    @GetMapping()
    public List<ChatRoom> roomList(){
        List<ChatRoom> chatRooms = chatRoomService.getAll();
        return chatRooms;
    }

    @GetMapping("{roomId}")
    public String showRoom(@PathVariable Long roomId){
        return roomId + "번 채팅방 조회완료";
    }

//    @PostMapping()
//    public ChatRoom createRoom(@RequestBody Map<String, Object> request){
//        ChatRoom chatRoom = chatRoomService.make(request.get("name").toString());
//        return chatRoom;
//    }

    @PostMapping()
    public ChatRoom createRoom(@RequestBody RequestCreateRoom requestCreateRoom){
        ChatRoom chatRoom = chatRoomService.make(requestCreateRoom.getName());
        return chatRoom;
    }

}
