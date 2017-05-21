package com.foodie.websocket;

import com.foodie.auth.UserSessionFilter;
import com.foodie.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import static com.foodie.auth.UserSessionFilter.FOODIE_USER;

/**
 * Created by gorg on 16.03.17.
 */
@Slf4j
class WebsocketHandler extends TextWebSocketHandler{



    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LOGGER.info("Websocket Message: {}",message);
        session.sendMessage(new TextMessage(message.asBytes()));
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("websocket session: {}", session);
        User user = (User) session.getAttributes().get(FOODIE_USER);
        user.setWebSocketSession(session);
        LOGGER.debug("User in websocket session: {}",user);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        User user = (User) session.getAttributes().get(FOODIE_USER);
        LOGGER.info("Removing web socket session for user: {}",user);
        user.setWebSocketSession(null);
    }
}
