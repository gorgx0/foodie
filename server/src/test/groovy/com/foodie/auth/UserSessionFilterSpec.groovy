package com.foodie.auth

import com.foodie.model.Group
import com.foodie.model.User
import com.foodie.util.UniqueKeyGeneratorImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultHandler
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.util.WebUtils
import spock.lang.Specification

import javax.servlet.http.Cookie
import java.util.concurrent.atomic.AtomicBoolean

import static com.foodie.auth.UserSessionFilter.FOODIE_USER
import static com.foodie.auth.UserSessionFilter.FOODIE_USER_COOKIE_NAME
import static com.foodie.auth.UserSessionFilter.INVITE_ID_PARAM_NAME
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by gorg on 29.04.17.
 */

@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
class UserSessionFilterSpec extends Specification {

    public static final String RETURNING_USER_ID = "ReturningUserId"
    public static final String REGISTERED_USER_ID = "RegisteredUserId"
    public static final String INVITATION_ID = "InvitationId"

    @Autowired
    WebApplicationContext webApplicationContext

    @Autowired
    MockMvc mvc

    @Autowired
    Map<String, Group> groups

    @Autowired
    Map<String, User> users

    def "new User with no session and no rememberMe cookie"() {
        given:
            def usersCount = users.size()
            def groupCount = groups.size()
        when:
            mvc.perform(get("/restaurants")).andExpect(status().isOk()).andDo(new ResultHandler() {
                @Override
                void handle(MvcResult result) throws Exception {
                    result
                }
            })
        then:
            users.size() == usersCount + 1
            groups.size() == groupCount + 1
            // check if the user has a group and the group is new
    }


    def "new User with invitation to join exisitng group"() {
        false
    }

    def "returning user with active group"() {
        false
    }

    def "returning user with no group"() {
        given:
            User u = new User()
            def returningUserId = RETURNING_USER_ID
            u.setId(returningUserId)
            MockHttpSession session = new MockHttpSession(webApplicationContext.servletContext)
            session.setAttribute(FOODIE_USER, null)
            users.put(u.id, u)
            Cookie c = new Cookie(FOODIE_USER_COOKIE_NAME, returningUserId)
        when:
            mvc.perform(get("/restaurants").cookie(c).session(session)).andExpect(status().isOk())
        then:
            User userFromSession = session.getAttribute(FOODIE_USER)
            userFromSession.lastGroup
            userFromSession.lastGroup.isNew
    }

    def "existing user with invitation to join new group"() {
        given:
            User user = new User()
            user.id = REGISTERED_USER_ID
            Group oldGroup = new Group("oldGroup")
            Group newGroup = new Group("newGroup")
            oldGroup.isNew = new AtomicBoolean(false)
            newGroup.isNew = new AtomicBoolean(false)
            groups.put(oldGroup.id,oldGroup)
            groups.put(newGroup.id,newGroup)
            user.lastGroup = oldGroup
            users.put(user.id,user)
            Cookie c = new Cookie(FOODIE_USER_COOKIE_NAME,user.id)
            MockHttpSession session = new MockHttpSession(webApplicationContext.servletContext)
        when:
            mvc.perform(get("/restaurants").cookie(c).param(INVITE_ID_PARAM_NAME,INVITATION_ID).session(session)).andExpect(status().isOk())
        then:
            User userFromSession = session.getAttribute(FOODIE_USER)
            userFromSession == user
            userFromSession.lastGroup == newGroup
    }

}
