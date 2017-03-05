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
                new Restaurant(1L, "restaurant01", true, BigDecimal.ZERO),
                new Restaurant(2L, "restaurant02", true, BigDecimal.ZERO),
                new Restaurant(4L, "restaurant03", true, BigDecimal.ZERO),
                new Restaurant(5L, "restaurant04", true, BigDecimal.ZERO),
                new Restaurant(6L, "restaurant05", true, BigDecimal.ZERO),
                new Restaurant(7L, "restaurant06", true, BigDecimal.TEN)
        );
    }

}
