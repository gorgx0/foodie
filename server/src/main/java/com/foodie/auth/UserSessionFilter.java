package com.foodie.auth;

import com.foodie.model.User;
import com.foodie.services.UserGroupService;
import com.foodie.services.UserGroupServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by gorg on 23.04.17.
 */
@Slf4j
@Component
public class UserSessionFilter implements Filter {

    public static final String FOODIE_USER = "FOODIE_USER";

    @Autowired
    private UserGroupServiceImpl userGroupService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Starting UserSessionFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(true);
        if(session.isNew()){
            LOGGER.debug("Creating new session");
            User user = userGroupService.getUser(null, null);
            session.setAttribute(FOODIE_USER,user);
        }else {
            User foodieUser = (User) session.getAttribute(FOODIE_USER);
            LOGGER.debug("Returning user: {}",foodieUser);
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("Destroy UserSessionFilter");
    }
}
