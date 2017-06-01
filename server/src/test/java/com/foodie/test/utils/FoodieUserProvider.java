package com.foodie.test.utils;

import com.foodie.auth.UserSessionFilter;
import com.foodie.model.User;
import com.foodie.services.UserGroupService;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.HttpCookieStore;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.stereotype.Component;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by gorg on 31.05.17.
 */
@Component
public class FoodieUserProvider {

    @Getter
    @Setter
    private int httpPort=-1 ;

    private final UserGroupService userGroupService;

    public FoodieUserProvider(@Autowired UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    public FoodieUser getUser() throws Exception {
        if(httpPort==-1){
            throw new IllegalStateException("FoodieUserProvider has not set httpPort");
        }
        User user = userGroupService.createNewUser();
        ClientUpgradeRequest upgradeRequest = new ClientUpgradeRequest();
        Object httpSession = upgradeRequest.getSession();
        HttpCookie userCookie = new HttpCookie(UserSessionFilter.FOODIE_USER_COOKIE_NAME, user.getId());
        SimpleWebsocketClient websocketClient = new SimpleWebsocketClient();
        upgradeRequest.setCookies(Arrays.asList(userCookie));
        WebSocketClient client = new WebSocketClient();
        client.start();
        URI webSocketUri = new URI(getWebsocketUri());
        Future<Session> connect = client.connect(websocketClient, webSocketUri, upgradeRequest);
        Session session = connect.get(5, TimeUnit.SECONDS);
        HttpClient httpClient = new HttpClient();
        HttpCookieStore cookieStore = new HttpCookieStore();
        cookieStore.add(new URI("http://localhost:"+httpPort+"/"),new HttpCookie(UserSessionFilter.FOODIE_USER_COOKIE_NAME,user.getId()));
        httpClient.setCookieStore(cookieStore);
        httpClient.start();
        FoodieUser foodieUser = new FoodieUser(user,session,httpClient);
        return foodieUser;
    }
    
    private String getWebsocketUri(){
        return  "ws://localhost:" + httpPort + "/websocket";
    }
}
