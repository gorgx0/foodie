package com.foodie.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by gorg on 16.03.17.
 */
@Slf4j
public class WebsocketHandler extends TextWebSocketHandler{

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LOGGER.info("Websocket Message: {}",message);
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("websocket session: {}", session);
    }
}
