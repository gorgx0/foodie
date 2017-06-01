package com.foodie.rest;

import com.foodie.auth.UserSessionFilter;
import com.foodie.model.User;
import com.foodie.services.UserGroupServiceImpl;
import com.foodie.test.utils.SimpleWebsocketClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.*;

import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.notNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by gorg on 17.05.17.
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TmpWebsocketTest {

    private String WEBSOCKET_URI_STRING = null ;
    private URI WEBSOCKET_URI = null;

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc ;

    @Mock
    private WebSocketHandler webSocketHandler;

    @Mock
    private ListenableFutureCallback<WebSocketSession> callback;

    @Autowired
    private UserGroupServiceImpl userGroupService;


    public TmpWebsocketTest() throws URISyntaxException {
    }

    @Before
    public void setUp() throws Exception {
        WEBSOCKET_URI_STRING = "ws://localhost:" + port + "/websocket";
        WEBSOCKET_URI = new URI(WEBSOCKET_URI_STRING);
    }

    @Test
    public void testJettyWebsocketClient() throws Exception {

        ClientUpgradeRequest upgradeRequest = new ClientUpgradeRequest();
        Object httpSession = upgradeRequest.getSession();
        User newUser = userGroupService.createNewUser();
        HttpCookie userCookie = new HttpCookie(UserSessionFilter.FOODIE_USER_COOKIE_NAME, newUser.getId());
        SimpleWebsocketClient websocketClient = new SimpleWebsocketClient();
        upgradeRequest.setCookies(Arrays.asList(userCookie));
        WebSocketClient client = new WebSocketClient();
        client.start();
        Future<Session> connect = client.connect(websocketClient,WEBSOCKET_URI, upgradeRequest);
        Session session = connect.get(1, TimeUnit.SECONDS);
        assertNotNull(newUser.getWebSocketSession());
        session.close();
    }

}
