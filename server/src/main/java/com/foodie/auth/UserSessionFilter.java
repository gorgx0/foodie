package com.foodie.auth;

import com.foodie.model.User;
import com.foodie.services.UserGroupService;
import com.foodie.services.UserGroupServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
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
    public static final String FOODIE_USER_COOKIE_NAME = "FOODIE_USER";


    @Autowired
    private UserGroupServiceImpl userGroupService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Starting UserSessionFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie userCookie = WebUtils.getCookie(httpServletRequest, FOODIE_USER_COOKIE_NAME);
        HttpSession session = httpServletRequest.getSession(true);
        if(null==userCookie){
            LOGGER.debug("Creating new session");
            User user = userGroupService.createNewUser(session.getId());
            session.setAttribute(FOODIE_USER,user);
        }else {
            String userCookieValue = userCookie.getValue();
            if (userCookieValue != null) {
                User user = userGroupService.getUser(userCookieValue);
                session.setAttribute(FOODIE_USER,user);
                LOGGER.debug("Returning user: {}",user);
            }else {
                LOGGER.warn("User cookie has no value");
            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("Destroy UserSessionFilter");
    }
}
