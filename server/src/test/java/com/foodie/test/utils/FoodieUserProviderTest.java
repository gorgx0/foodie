package com.foodie.test.utils;

import com.foodie.auth.UserSessionFilter;
import com.foodie.services.UserGroupServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.api.ContentResponse;
import org.hamcrest.Matcher;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.base.Predicates.notNull;
import static java.util.function.Predicate.isEqual;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;

/**
 * Created by gorg on 01.06.17.
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FoodieUserProviderTest {

    @LocalServerPort
    private int httpPort;


    @Autowired
    private FoodieUserProvider provider;

    @Autowired
    UserGroupServiceImpl userGroupService;

    @Before
    public void setUp() throws Exception {
        provider.setHttpPort(httpPort);
    }

    @Test
    public void shouldGetUser() throws Exception {
        FoodieUser user = provider.getUser();
        assertNotNull(user);
        assertNotNull(user.getHttpClient());
        assertNotNull(user.getUser());
        assertNotNull(user.getWebsocketSession());
        assertNotNull(user.getUser().getWebSocketSession());
    }

    @Test
    public void shouldGetTwoUsersToSameGroupViaInvitation() throws Exception {
        FoodieUser user1 = provider.getUser();
        FoodieUser user2 = provider.getUser();
        String inviteId = userGroupService.createInviteId(user1.getUser().getLastGroup());
        ContentResponse response = user2.getHttpClient().GET(getInviteUri(inviteId));
        assertEquals(response.getStatus(),200);
        assertThat(user1.getUser().getLastGroup(),is(user2.getUser().getLastGroup()));
    }

    private String getInviteUri(String inviteId) {
        String inviteUrl = new StringBuilder("http://localhost:")
                .append(provider.getHttpPort())
                .append("/invite?")
                .append(UserSessionFilter.INVITE_ID_PARAM_NAME)
                .append("=")
                .append(inviteId)
                .toString();
        return inviteUrl;
    }
}
