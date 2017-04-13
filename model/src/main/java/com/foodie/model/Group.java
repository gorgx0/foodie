package com.foodie.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by gorg on 11.04.17.
 */
public class Group {

    @Getter
    private final String groupId;

    @Getter
    private final Set<User> users = new HashSet<>();

    @Getter
    private Set<Restaurant> restaurants = new HashSet<>();

    @Getter
    private Map<Restaurant, AtomicInteger> votes = new ConcurrentHashMap<>();

    public Group(String groupId) {
        this.groupId = groupId;
    }

}
