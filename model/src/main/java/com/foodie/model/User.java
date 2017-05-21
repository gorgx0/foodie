package com.foodie.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * Created by gorg on 11.04.17.
 */
@Data
@RequiredArgsConstructor
public class User {

    private final String id ;
    private Group lastGroup ;
    private WebSocketSession webSocketSession;

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }
}
