package com.foodie.services;

import com.foodie.model.Group;
import com.foodie.model.Restaurant;

/**
 * Created by gorg on 23.04.17.
 */
public interface GroupService {

    void addRestaurant(Group group, String name);

    void removeResturant(Group group, Restaurant restaurant);

}
