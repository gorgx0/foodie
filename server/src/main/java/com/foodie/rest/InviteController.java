package com.foodie.rest;

import com.foodie.model.Group;
import com.foodie.model.User;
import com.foodie.services.UserGroupServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.foodie.auth.UserSessionFilter.FOODIE_USER;

/**
 * Created by gorg on 06.05.17.
 */
@Slf4j
@RestController
@RequestMapping("/invite")
public class InviteController {


    @Autowired
    private UserGroupServiceImpl userGroupService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody public String invitedId(@Autowired HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(FOODIE_USER);
        String inviteId = null ;
        if (user == null) {
            LOGGER.warn("No user in session");
        }else {
            Group g = user.getLastGroup();
            if (g != null) {
                inviteId = userGroupService.createInviteId(g);
            }else {
                LOGGER.warn("Found user with no current group");
            }
        }
        return inviteId;
    }
}
