package com.foodie.auth;

import com.foodie.model.Group;
import com.foodie.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gorg on 11.04.17.
 */
@Component
public class UserSettingFilter implements Filter {

    public static final String USER_COOKIE_NAME = "USER_COOKIE";
    public static final String GROUP_COOKIE_NAME = "GROUP_COOKIE";

    @Autowired
    UserGroupService userGroupService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            cookies = new Cookie[0];
        }
        Map<String, Cookie> cookieNamesMap = Stream.of(cookies).collect(Collectors.toMap(Cookie::getName, Function.identity()));
        User u;
        if(!cookieNamesMap.containsKey(USER_COOKIE_NAME)){
            Cookie userCookie = new Cookie(USER_COOKIE_NAME, "cookieRandomValue");
            res.addCookie(userCookie);
            u = new User();
            userGroupService.setUser(userCookie);
        }else {
            u = userGroupService.getUser(cookieNamesMap.get(USER_COOKIE_NAME));
        }
        if(!cookieNamesMap.containsKey(GROUP_COOKIE_NAME)){
            Cookie groupCookie = new Cookie(GROUP_COOKIE_NAME, "cookieRandomValue");
            res.addCookie(groupCookie);
            Group lastGroup = u.getLastGroup();
            if (lastGroup == null) {
                Group group = new Group();
                u.setLastGroup(group);
            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }

}
