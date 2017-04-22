package com.foodie;

import com.foodie.auth.UserGroupService;
import com.foodie.auth.UserGroupServiceImpl;
import com.foodie.model.Group;
import com.foodie.util.UniqueKeyGenerator;
import com.foodie.util.UniqueKeyGeneratorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by gorg on 02.03.17.
 *
 * Foodie application is supposed to support online food ordering in groups
 *
 */
@SpringBootApplication
public class Main {

    private Map<String, Group> groupsMap = new HashMap<>();


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean(name = "groups")
    public Map<String, Group> getGroups() {
        return groupsMap ;
    }

    @Bean
    public UniqueKeyGenerator groupIdGenerator() {
        return new UniqueKeyGeneratorImpl(groupsMap);
    }
}
