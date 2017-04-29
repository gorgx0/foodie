package com.foodie.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gorg on 23.04.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringSecurityTesting {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Test
    public void name() throws Exception {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        MockHttpSession session = new MockHttpSession(context.getServletContext());
        mockMvc.perform(get("/restaurants").session(session))
                .andExpect(status().isOk())
        ;
        Object securityContext = session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        assertThat(securityContext,notNullValue());
    }
}
