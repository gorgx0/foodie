package com.foodie;

import com.foodie.auth.AuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by gorg on 02.03.17.
 */
@SpringBootApplication(scanBasePackages = {"com.foodie.rest", "com.foodie.auth"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Bean
    public FilterRegistrationBean filters() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        filterRegistrationBean.setFilter(authenticationFilter);
        return filterRegistrationBean;
    }

}
