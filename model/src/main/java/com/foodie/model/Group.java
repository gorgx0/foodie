package com.foodie.model;

import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gorg on 11.04.17.
 */
@ToString(exclude = {"users"})
public class Group {

    @Getter
    private final String id;

    @Getter
    private final Set<User> users = new HashSet<>();

    @Getter
    private Set<Restaurant> restaurants = new HashSet<>();

    @Getter
    private Map<Restaurant, AtomicInteger> votes = new ConcurrentHashMap<>();

    @Getter
    private AtomicBoolean isNew = new AtomicBoolean(true);

    public Group(String id) {
        this.id = id;
    }

}
