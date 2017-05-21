package com.foodie.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * Created by gorg on 16.03.17.
 */
@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer{


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        HandshakeInterceptor sessionInterceptor = new HttpSessionHandshakeInterceptor();
        registry.addHandler(new WebsocketHandler(),"/websocket").addInterceptors(sessionInterceptor).setAllowedOrigins("*");

    }
}
