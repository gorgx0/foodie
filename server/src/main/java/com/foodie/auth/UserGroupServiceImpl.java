package com.foodie.auth;

import com.foodie.model.Group;
import com.foodie.model.User;
import com.foodie.util.RandomStringProvider;
import com.foodie.util.UniqueKeyGenerator;
import com.foodie.util.UniqueKeyGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by gorg on 11.04.17.
 */
@Component
public class UserGroupServiceImpl implements UserGroupService {

    private Map<String, Group> groupsMap = new HashMap<>();


    @Autowired
    UniqueKeyGenerator groupIdGenerator ;


    @Override
    public User getUser(String sessionId, String invitationId) {

        User u = null;

        if(sessionId==null && invitationId==null){
            u = new User();
            Group newGroup = new Group(groupIdGenerator.getUniqueKey());
            groupsMap.put(newGroup.getGroupId(), newGroup);
            u.setLastGroup(newGroup);
        }
        return u;
    }


}
