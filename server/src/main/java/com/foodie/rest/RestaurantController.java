package com.foodie.rest;

import com.foodie.auth.UserSessionFilter;
import com.foodie.model.Restaurant;
import com.foodie.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.foodie.auth.UserSessionFilter.FOODIE_USER;

/**
 * Created by gorg on 02.03.17.
 *
 *
 */
@Slf4j
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Restaurant> getRestaurants(@Autowired HttpSession session) {
        return ((User) session.getAttribute(FOODIE_USER)).getLastGroup().getRestaurants();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addRestaurant(@RequestBody Restaurant restaurant, @SessionAttribute(FOODIE_USER) User user){
        LOGGER.debug("User {} adds new restaurants {}", user,restaurant);
        user.getLastGroup().getRestaurants().add(restaurant);
    }
}
