package com.foodie.util;

/**
 * Created by gorg on 06.05.17.
 */
public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(String id) {
        super("Group not found for id="+id);
    }
}
