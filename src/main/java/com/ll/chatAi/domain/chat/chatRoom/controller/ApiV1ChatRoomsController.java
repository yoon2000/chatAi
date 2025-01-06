package com.ll.chatAi.domain.chat.chatRoom.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chat/rooms")
public class ApiV1ChatRoomsController {

    @GetMapping()
    public String roomList(){
        return "채팅방 목록 조회완료";
    }

    @GetMapping("{roomId}")
    public String showRoom(@PathVariable Long roomId){
        return roomId + "번 채팅방 조회완료";
    }

    @PostMapping()
    public String createRoom(@PathVariable Long roomId){
        return "채팅방 생성 완료";
    }

}
