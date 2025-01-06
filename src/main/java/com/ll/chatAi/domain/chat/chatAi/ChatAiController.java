package com.ll.chatAi.domain.chat.chatAi;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1/chat")
@RestController
public class ChatAiController {
    private final OpenAiChatModel openAiChatModel;

    public ChatAiController(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }

    @GetMapping("/ai")
    public Map<String, String> chat(@RequestBody String message) {
        Map<String, String> responses = new HashMap<>();

        String openAiResponse = openAiChatModel.call(message);
        responses.put("openAi - ChatGPT 4 응답", openAiResponse);

        return responses;
    }

}
