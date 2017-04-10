package com.foodie.auth;

import com.foodie.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

/**
 * Created by gorg on 11.04.17.
 */
public interface UserGroupService {

    User getUser(Cookie cookie) ;

    void setUser(Cookie cookie);
}
