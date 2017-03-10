package com.foodie.auth;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by gorg on 09.03.17.
 */

@Slf4j

public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("AuthenticationFilter starting");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(true);
        if (session.isNew()) {
            httpResponse.sendRedirect(((HttpServletRequest) request).getRequestURL().toString());
            return;
        }
        Cookie[] cookies = httpRequest.getCookies();
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.debug("AuthenticationFilter destroying");
    }
}
