package com.foodie.auth

import com.foodie.Main
import com.foodie.model.Group
import com.foodie.model.Restaurant
import com.foodie.model.User
import com.foodie.services.UserGroupServiceImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.ApplicationContext
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultHandler
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import javax.servlet.http.Cookie

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK

/**
 * Created by gorg on 29.04.17.
 */

@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
class UserSessionFilterSpec extends Specification {

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
            users.size()==usersCount+1
            groups.size()==groupCount+1
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
        def returningUserId = "ReturningUserId"
        u.setId(returningUserId)
        MockHttpSession session = new MockHttpSession(webApplicationContext.servletContext)
        session.setAttribute(UserSessionFilter.FOODIE_USER, null)
        users.put(u.id, u)
        Cookie c = new Cookie(UserSessionFilter.FOODIE_USER_COOKIE_NAME, returningUserId)
        when:
        mvc.perform(get("/restaurants").cookie(c).session(session)).andExpect(status().isOk())
        then:
        User userFromSession = session.getAttribute(UserSessionFilter.FOODIE_USER)
        userFromSession.lastGroup
        userFromSession.lastGroup.isNew
    }

    def "existing user with invitation to join new group"() {
        false
    }
}
