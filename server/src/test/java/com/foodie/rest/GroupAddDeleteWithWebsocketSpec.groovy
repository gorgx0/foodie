package com.foodie.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.foodie.auth.UserSessionFilter
import com.foodie.model.Restaurant
import com.foodie.model.User
import com.foodie.services.UserGroupServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static com.foodie.auth.UserSessionFilter.FOODIE_USER
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by gorg on 07.05.17.
 */
@SpringBootTest
@AutoConfigureMockMvc
class GroupAddDeleteWithWebsocketSpec extends Specification{

    @Autowired
    private MockMvc mvc

    @Autowired
    private UserGroupServiceImpl userGroupService

    def "user adds a new restaurant to current Group"() {
        given:
            User user = userGroupService.createNewUser();
            Restaurant restaurant = new Restaurant(1l,"new Restaurant")
            MockHttpSession session = new MockHttpSession()
            session.setAttribute(FOODIE_USER,user)
        when:
            mvc
                .perform(post("/restaurants")
                    .session(session)
                    .contentType(APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(restaurant)))
                .andExpect(status().isOk())
        then:
            user.lastGroup.restaurants.contains(restaurant)
    }


}
