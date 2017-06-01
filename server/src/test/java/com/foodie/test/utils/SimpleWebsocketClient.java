package com.foodie.test.utils;

import com.foodie.rest.TmpWebsocketTest;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

/**
 * Created by gorg on 31.05.17.
 */
@Slf4j
public class SimpleWebsocketClient implements WebSocketListener {

    @Override
    public void onWebSocketBinary(byte[] payload, int offset, int len) {
        LOGGER.info("onWebSocketBinary: {}", payload);
    }

    @Override
    public void onWebSocketText(String message) {
        LOGGER.info("onWebSocketText: {}", message);
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        LOGGER.info("onWebSocketClose: {}", reason);
    }

    @Override
    public void onWebSocketConnect(Session session) {
        LOGGER.info("onWebSocketConnect: {}", session);
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        LOGGER.error("onWebSocketError: ", cause);
    }
}
