package com.foodie.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by gorg on 02.03.17.
 */
@SpringBootApplication(scanBasePackages = {"com.foodie.rest"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
