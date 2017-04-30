package com.foodie.auth;

import com.foodie.Main;
import com.foodie.model.Group;
import com.foodie.model.User;
import com.foodie.services.UserGroupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

/**
 * Created by gorg on 21.04.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserGroupServiceImplTest {


    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    Map<String, Group> groupMap;

    @Autowired
    Map<String, User> usersMap;

    @Before
    public void setUp() throws Exception {
        groupMap.clear();
        usersMap.clear();
    }

    @Test
    public void newUserWithoutInvite() throws Exception {
        User user = userGroupService.createNewUser("newUserId");
        assertThat(user,notNullValue());
        assertThat(user.getLastGroup(),notNullValue());
        assertThat(user.getLastGroup().getId(),notNullValue());
        assertTrue(user.getLastGroup().getId().length()>0);
        assertThat(user.getLastGroup().getRestaurants(),notNullValue());
        assertTrue(user.getLastGroup().getRestaurants().size()==0);
    }

    @Test
    public void newUserWithInvite() throws Exception {

        String invitationId = "invitationId";
        Group g = mock(Group.class);
        groupMap.put(invitationId, g);
        User u = userGroupService.getUser(null, invitationId);
        assertThat(u,notNullValue());
        assertThat(u.getLastGroup(),notNullValue());
        assertThat(u.getLastGroup().getRestaurants(),notNullValue());
    }

    @Test
    public void oldUserWithoutInvite() throws Exception {
        User u = mock(User.class);
        when(u.getLastGroup()).thenReturn(mock(Group.class));
        String userSessionId = "oldUserSessionId";
        usersMap.put(userSessionId, u);
        User user = userGroupService.getUser(userSessionId, null);
        assertThat(user,notNullValue());
        assertThat(user.getLastGroup(),notNullValue());
    }

    @Test
    public void oldUserWithInvite() throws Exception {
        String userSessionId = "oldUserSessionId";
        String inviteId = "inviteId";
        String oldGroupId = "oldGroupId";
        User u = mock(User.class);
        Group oldGroup = mock(Group.class);
        Group newGroup = mock(Group.class);
        String newGroupId = inviteId;
        when(oldGroup.getId()).thenReturn(oldGroupId);
        when(newGroup.getId()).thenReturn(newGroupId);
        when(u.getLastGroup()).thenReturn(oldGroup);
        when(u.getLastGroup()).thenReturn(mock(Group.class));
        groupMap.put(oldGroupId, oldGroup);
        groupMap.put(newGroupId, newGroup);
        usersMap.put(userSessionId, u);

        User user = userGroupService.getUser(userSessionId, inviteId);

        assertThat(user,notNullValue());
        verify(u, times(1)).getLastGroup();
        verify(u, times(1)).setLastGroup(newGroup);
    }
}