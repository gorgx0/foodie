package com.foodie.auth;

import com.foodie.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNotNull;
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

    @Test
    public void newUserWithoutInvite() throws Exception {

        User user = userGroupService.getUser(null, null);
        assertThat(user,notNullValue());
        assertThat(user.getLastGroup(),notNullValue());
        assertThat(user.getLastGroup().getGroupId(),notNullValue());
        assertTrue(user.getLastGroup().getGroupId().length()>0);
        assertThat(user.getLastGroup().getRestaurants(),notNullValue());
        assertTrue(user.getLastGroup().getRestaurants().size()==0);
    }

    @Test
    public void newUserWithInvite() throws Exception {
        assertTrue(false);
    }

    @Test
    public void oldUserWithoutInvite() throws Exception {
        assertTrue(false);
    }

    @Test
    public void oldUserWithInvite() throws Exception {
        assertTrue(false);
    }
}