package com.foodie.auth;

import com.foodie.model.User;

import javax.servlet.http.Cookie;

/**
 * Created by gorg on 11.04.17.
 */
public interface UserGroupService {

    User getUser(String sessionId) ;

    void setUser(String sessionId);
}
