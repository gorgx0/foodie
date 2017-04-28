package com.foodie.rest;

import com.foodie.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by gorg on 02.03.17.
 *
 *
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @SuppressWarnings("unused")
    @RequestMapping(method = RequestMethod.GET)
    Collection<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant(2L, "restaurant02", 0),
                new Restaurant(1L, "restaurant01", 0),
                new Restaurant(4L, "restaurant03", 0),
                new Restaurant(5L, "restaurant04", 0),
                new Restaurant(6L, "restaurant05", 0),
                new Restaurant(7L, "restaurant06", 0)
        );
        return restaurants;

    }

}
