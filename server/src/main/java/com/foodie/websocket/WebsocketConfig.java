package com.foodie.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by gorg on 16.03.17.
 */
@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer{


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebsocketHandler(),"/websocket").setAllowedOrigins("*");
    }
}
