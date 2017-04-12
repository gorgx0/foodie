package com.foodie.auth;

import com.foodie.Main;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by gorg on 12.04.17.
 *
 * Testing the user management
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
public class RestaurantControllerSecurityTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterRegistrationBean registrationBean;


    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(registrationBean.getFilter(),"/*")
                .build();
    }

    @Test
    public void template() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/restaurants")).andExpect(MockMvcResultMatchers.cookie().exists("USER_COOKIE"));
        LOGGER.info(resultActions.andReturn().getResponse().getContentAsString());
    }


    @Test
    public void newUser() throws Exception {
        assertThat("Not implemented yet.",true, is(false));
    }

    @Test
    public void invitedUser() throws Exception {
        assertThat("Not implemented yet.",true, is(false));
    }

    @Test
    public void existingUser() throws Exception {
        assertThat("Not implemented yet.",true, is(false));
    }
}
