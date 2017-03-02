package com.foodie.rest;

import com.foodie.model.Restaurant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by gorg on 02.03.17.
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantControler {

    @RequestMapping(method = RequestMethod.GET)
    Collection<Restaurant> getRestaurants() {
        return Arrays.asList(
                new Restaurant("restaurant01", true, BigDecimal.ZERO),
                new Restaurant("restaurant02", true, BigDecimal.TEN)
        );
    }

}
