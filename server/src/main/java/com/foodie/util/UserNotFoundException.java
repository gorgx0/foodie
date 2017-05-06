package com.foodie.util;

/**
 * Created by gorg on 06.05.17.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("User not found for id="+id);
    }
}
