package com.foodie.auth;

import com.foodie.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by gorg on 20.04.17.
 */
@Component
public class FoodieSecurityContextRepository implements SecurityContextRepository{

    @Autowired
    private UserGroupService userGroupService;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        HttpSession session = request.getSession(false);
        if (session == null){
            session = request.getSession(true);
        }
        String sessionId = session.getId();
        String inviteId = request.getParameter("INVITE");

        User user = userGroupService.getUser(sessionId, inviteId);
        Object sessionAttribute = session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

        return new SecurityContextImpl();
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }
}
