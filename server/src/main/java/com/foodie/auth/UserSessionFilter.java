package com.foodie.auth;

import com.foodie.model.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by gorg on 23.04.17.
 */
@Slf4j
public class UserSessionFilter implements Filter {

    public static final String FOODIE_USER = "FOODIE_USER";

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
        }else {
            User foodie_user = (User) session.getAttribute(FOODIE_USER);
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("Destroy UserSessionFilter");
    }
}
