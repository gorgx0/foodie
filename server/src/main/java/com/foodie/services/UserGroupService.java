package com.foodie.services;

import com.foodie.model.Group;
import com.foodie.model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

/**
 * Created by gorg on 11.04.17.
 */
public interface UserGroupService {

    User createNewUser();

    User getUser(String userCookieValue);

    void applyInvite(String userCookieValue, String invitationId);

    User createNewUserWithInvite(String inivationId);

    String createInviteId(Group group);
}
