package com.foodie.auth;

import com.foodie.model.User;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gorg on 11.04.17.
 */
public class UserGroupServiceImpl implements UserGroupService {

    private final Map<String , User> register = new HashMap<>();

    @Override

    public User getUser(String  sessionId) {
        return register.get(sessionId);
    }

    @Override
    public void setUser(String  sessionId) {
        register.put(sessionId, new User());
    }


}
