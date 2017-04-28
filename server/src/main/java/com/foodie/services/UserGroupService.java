package com.foodie.services;

import com.foodie.model.User;

import javax.servlet.http.Cookie;

/**
 * Created by gorg on 11.04.17.
 */
public interface UserGroupService {

    User getUser(String sessionId, String invitationId) ;

}
