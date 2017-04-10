package com.foodie;

import com.foodie.auth.UserGroupService;
import com.foodie.auth.UserGroupServiceImpl;
import com.foodie.auth.UserSettingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;

/**
 * Created by gorg on 02.03.17.
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

    @Bean
    public FilterRegistrationBean registration(UserSettingFilter filter){
        FilterRegistrationBean frb = new FilterRegistrationBean(filter);
        frb.setEnabled(false);
        return frb;
    }
}
