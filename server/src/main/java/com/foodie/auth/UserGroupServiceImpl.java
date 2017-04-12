package com.foodie.auth;

import com.foodie.model.User;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gorg on 11.04.17.
 */
public class UserGroupServiceImpl implements UserGroupService {

    private final Map<Cookie, User> register = new HashMap<>();

    @Override

    public User getUser(Cookie cookie) {
        return register.get(cookie);
    }

    @Override
    public void setUser(Cookie cookie) {
        register.put(cookie, new User());
    }


}
