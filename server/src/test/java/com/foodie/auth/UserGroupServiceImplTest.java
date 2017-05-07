package com.foodie.auth;

import com.foodie.model.Group;
import com.foodie.model.User;
import com.foodie.services.UserGroupService;
import com.foodie.util.InvitationNotFoundException;
import com.foodie.util.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
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

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserGroupServiceImplTest {


    @Autowired
    private UserGroupService userGroupService;

    @Qualifier("groups")
    @Autowired
    Map<String, Group> groupMap;

    @Qualifier("users")
    @Autowired
    Map<String, User> usersMap;

    @Autowired
    private Map invites;


    @Before
    public void setUp() throws Exception {
        groupMap.clear();
        usersMap.clear();
    }

    @Test
    public void newUserWithoutInvite() throws Exception {
        User user = userGroupService.createNewUser();
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
        invites.put(invitationId, g);
        User u = userGroupService.createNewUserWithInvite(invitationId);
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
        User user = userGroupService.getUser(userSessionId);
        assertThat(user,notNullValue());
        assertThat(user.getLastGroup(),notNullValue());
    }

    @Test
    public void oldUserWithInvite() throws Exception {
        String userSessionId = "oldUserSessionId";
        String inviteId = "inviteId";
        String oldGroupId = "oldGroupId";
        String newGroupId = "newGroupId";
        User u = mock(User.class);
        Group oldGroup = mock(Group.class);
        Group newGroup = mock(Group.class);
        when(oldGroup.getId()).thenReturn(oldGroupId);
        when(newGroup.getId()).thenReturn(newGroupId);
        when(u.getLastGroup()).thenReturn(oldGroup);
        when(u.getLastGroup()).thenReturn(mock(Group.class));
        groupMap.put(oldGroupId, oldGroup);
        groupMap.put(newGroupId, newGroup);
        invites.put(inviteId, newGroup);
        usersMap.put(userSessionId, u);

        userGroupService.applyInvite(userSessionId, inviteId);

        verify(u, times(1)).setLastGroup(newGroup);
    }


    @Test(expected = UserNotFoundException.class)
    public void testForNonExistingUSer() throws Exception {
        String notExistingUserId = "notExistingUserId";
        if(usersMap.remove(notExistingUserId)!=null){
            LOGGER.warn("User exsited");
        }
        userGroupService.getUser(notExistingUserId);
    }


    @Test(expected = InvitationNotFoundException.class)
    public void testapplyInfiveForNonExistingInvite() throws Exception {
        User user = userGroupService.createNewUser();
        String notExistingInviteId = "notExistingInviteId";
        if(invites.remove(notExistingInviteId)!=null){
            LOGGER.warn("InviteExisted");
        }
        userGroupService.applyInvite(user.getId(),notExistingInviteId);
    }

    @Test(expected = UserNotFoundException.class)
    public void testApplyInviteForNonExistingUser() throws Exception {
        String notExistingUserId = "notExistingUserId";
        if(usersMap.remove(notExistingUserId)!=null){
            LOGGER.warn("User exsited");
        }
        userGroupService.applyInvite(notExistingUserId,"someOtherInviteId");
    }

    @Test(expected = InvitationNotFoundException.class)
    public void testCresteNewUserForNonExistingInvite() throws Exception {
        User u = userGroupService.createNewUser();
        String inviteId = userGroupService.createInviteId(u.getLastGroup());
        String notExistingInviteId = "notExistingInviteId";
        if(invites.remove(notExistingInviteId)!=null){
            LOGGER.warn("InviteExisted");
        }
        User userCreated = userGroupService.createNewUserWithInvite(notExistingInviteId);
    }
}