package com.foodie;

import com.foodie.auth.UserGroupService;
import com.foodie.auth.UserGroupServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by gorg on 02.03.17.
 *
 * Foodie application is supposed to support online food ordering in groups
 *
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public UserGroupService userGroupService() {
        return new UserGroupServiceImpl();
    }

}
