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
import javax.servlet.http.HttpServletResponse;
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
    public static final String INVITE_ID_PARAM_NAME = "inviteId";


    @Autowired
    private UserGroupServiceImpl userGroupService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Starting UserSessionFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Cookie userCookie = WebUtils.getCookie(httpServletRequest, FOODIE_USER_COOKIE_NAME);
        HttpSession session = httpServletRequest.getSession(true);
        User user = (User) session.getAttribute(FOODIE_USER);
        if(null==userCookie && null==user){
            LOGGER.debug("Creating new session");
            user = userGroupService.createNewUser();
            session.setAttribute(FOODIE_USER,user);
            Cookie cookie = new Cookie(FOODIE_USER_COOKIE_NAME, user.getId());
            cookie.setMaxAge(Integer.MAX_VALUE);
            httpServletResponse.addCookie(cookie);
        }else {
            if(user==null){
                if (userCookie != null) {
                    user = userGroupService.getUser(userCookie.getValue());
                    LOGGER.debug("Returning user: {}", user);
                }
            }
            session.setAttribute(FOODIE_USER, user);
        }
        String inviteId = httpServletRequest.getParameter(INVITE_ID_PARAM_NAME);
        if (inviteId != null) {
            userGroupService.applyInvite(userCookie.getValue(),inviteId);
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("Destroy UserSessionFilter");
    }
}
