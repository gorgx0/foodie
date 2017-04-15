package com.foodie.auth;

import com.foodie.model.Group;
import com.foodie.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gorg on 11.04.17.
 *
 * This is main entry point to the app.
 * Here user is recognized or created
 *
 */
@Component
public class UserSettingFilter implements Filter {

    private static final String USER_COOKIE_NAME = "USER_COOKIE";
    private static final String GROUP_COOKIE_NAME = "GROUP_COOKIE";

    private final UserGroupService userGroupService;

    @Autowired
    public UserSettingFilter(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }

}
