package com.foodie.test.utils;

import com.foodie.model.User;
import lombok.Data;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.websocket.api.Session;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by gorg on 31.05.17.
 */
@Data
public class FoodieUser {

    private final User user;
    private final Session websocketSession ;
    private final HttpClient httpClient;

}
