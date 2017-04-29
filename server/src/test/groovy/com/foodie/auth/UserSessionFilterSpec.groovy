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
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification
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

    def "test Method"() {
        given:
            def usersCount = users.size()
            def groupCount = groups.size()
        when:
            mvc.perform(get("/restaurants")).andExpect(status().isOk())
        then:
            users.size()==usersCount+1
            groups.size()==groupCount+1
    }

}
