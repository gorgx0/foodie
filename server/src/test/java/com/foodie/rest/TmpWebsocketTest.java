package com.foodie.rest;

import com.foodie.Main;
import com.foodie.auth.UserSessionFilter;
import com.foodie.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.servlet.http.Cookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gorg on 17.05.17.
 */

@Slf4j
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Main.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@WebAppConfiguration
@AutoConfigureMockMvc
public class TmpWebsocketTest {

    private final String WEBSOCKET_URI_STRING = "ws://lwocalhost:8080/websocket";
    private final URI WEBSOCKET_URI = new URI(WEBSOCKET_URI_STRING);

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc ;

    @Mock
    private WebSocketHandler webSocketHandler;

    @Mock
    private ListenableFutureCallback<WebSocketSession> callback;

    public TmpWebsocketTest() throws URISyntaxException {
    }


    @Test
    public void testwebsocketPresence() throws Exception {

        WebSocketClient webSocketClient = new StandardWebSocketClient();
        ListenableFuture<WebSocketSession> webSocketSessionListenableFuture = webSocketClient.doHandshake(webSocketHandler, null, WEBSOCKET_URI);
        webSocketSessionListenableFuture.addCallback(callback);

        verify(webSocketHandler, timeout(1000)).afterConnectionEstablished(any(WebSocketSession.class));
        verify(callback, timeout(1000)).onSuccess(any(WebSocketSession.class));
    }

    @Test
    public void testWebsocketHandler() throws Exception {

        MockHttpSession session = new MockHttpSession();
        MvcResult mvcResult = mockMvc.perform(get("/restaurants").session(session)).andExpect(status().isOk()).andReturn();
        User user = (User) session.getAttribute(UserSessionFilter.FOODIE_USER);
        assertNotNull(user);

        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketConnectionManager webSocketConnectionManager = new WebSocketConnectionManager(webSocketClient, webSocketHandler, WEBSOCKET_URI_STRING, user);
        webSocketConnectionManager.start();
        webSocketConnectionManager.
        verify(webSocketHandler,timeout(1000)).afterConnectionEstablished(any(WebSocketSession.class));
    }

}
