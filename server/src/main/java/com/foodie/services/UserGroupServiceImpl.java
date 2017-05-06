package com.foodie.services;

import com.foodie.model.Group;
import com.foodie.model.User;
import com.foodie.util.InvitationNotFoundException;
import com.foodie.util.UniqueKeyGenerator;
import com.foodie.util.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by gorg on 11.04.17.
 */
@Slf4j
@Component
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    Map<String, Group> groups;

    @Autowired
    Map<String, User> users;

    @Autowired
    private Map<String, Group> invites;

    @Autowired
    UniqueKeyGenerator groupIdGenerator ;

    @Autowired
    private UniqueKeyGenerator userIdGenerator;

    @Autowired
    private UniqueKeyGenerator inviteIdGenerator;


    @Override
    public User createNewUser() {
        User u = new User(userIdGenerator.getUniqueKey());
        Group newGroup = new Group(groupIdGenerator.getUniqueKey());
        groups.put(newGroup.getId(), newGroup);
        u.setLastGroup(newGroup);
        users.put(u.getId(), u);
        return u;
    }



    @Override
    public User getUser(String userCookieValue) {
        User userFromCookie = users.get(userCookieValue);
        if(null == userFromCookie.getLastGroup()){
            LOGGER.info("User with no group");
            Group g = new Group(groupIdGenerator.getUniqueKey());
            userFromCookie.setLastGroup(g);
        }
        return userFromCookie;
    }

    @Override
    public void applyInvite(String userCookieValue, String invitationId) {
        User u = users.get(userCookieValue);
        if (null == u) {
            LOGGER.warn("User not found for id={}",userCookieValue);
            throw new UserNotFoundException(userCookieValue);
        }
        Group g = invites.get(invitationId);
        if (g == null) {
            LOGGER.warn("Invitation not found for id={}", invitationId);
            throw new InvitationNotFoundException(invitationId);
        }
        u.setLastGroup(g);
    }

    @Override
    public User createNewUserWithInvite(String inivationId) {
        Group g = invites.get(inivationId);
        User u = null;
        if (g != null) {
            u = new User(userIdGenerator.getUniqueKey());
            u.setLastGroup(g);
        }else {
            LOGGER.warn("Invitation not found for id={}",inivationId);
            throw new InvitationNotFoundException(inivationId);
        }
        return u;
    }

    @Override
    public String createInviteId(Group group) {
        String id = inviteIdGenerator.getUniqueKey();
        invites.put(id, group);
        return id;
    }
}
