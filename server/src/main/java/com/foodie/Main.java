package com.foodie;

import com.foodie.auth.UserSessionFilter;
import com.foodie.model.Group;
import com.foodie.model.User;
import com.foodie.util.UniqueKeyGenerator;
import com.foodie.util.UniqueKeyGeneratorImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gorg on 02.03.17.
 *
 * Foodie application is supposed to support online food ordering in groups
 *
 */
@SpringBootApplication
public class Main {

    private Map<String, Group> groupsMap = new HashMap<>();

    private Map<String, User> usersMap = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean(name = "groups")
    public Map<String, Group> groups() {
        return groupsMap ;
    }

    @Bean(name = "users")
    public Map<String, User> users() {
        return usersMap;
    }

    @Bean
    public UniqueKeyGenerator groupIdGenerator() {
        return new UniqueKeyGeneratorImpl(groupsMap);
    }

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new UserSessionFilter());
//        return filterRegistrationBean;
//    }
}
