package com.ll.chatAi.global.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 수신할 수 있는 경로 설정
        registry.enableSimpleBroker("/topic");  // 메시지를 받을 경로 설정
        registry.setApplicationDestinationPrefixes("/app"); // 클라이언트가 보낼 경로 설정
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 엔드포인트 등록
        registry.addEndpoint("/ws/chat")
                .setAllowedOrigins("https://cdpn.io", "https://chat-app-front2501.vercel.app", "http://127.0.0.1:5173") // 허용할 출처 설정
                .withSockJS();
    }
}

