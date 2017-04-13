package com.foodie.dao;

import com.foodie.model.Group;
import com.foodie.model.Restaurant;
import com.foodie.model.User;

/**
 * Created by gorg on 13.04.17.
 */
public interface FoodieDao {

    public void addRestaurant(String sessionId, Restaurant restaurant);

    public void addInvitedUser(String inviteId, String invitedUSerSessionId);

    public User findUserBySessionId(String sessionId);

    public Restaurant[] findRestaurantsForGroup(String sessionId);

    public Group getGroup(String sessionId);

}
