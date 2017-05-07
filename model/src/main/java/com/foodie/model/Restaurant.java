package com.foodie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by gorg on 02.03.17.
 */
@Data
@NoArgsConstructor
public class Restaurant {

    private Long id;
    private String name ;
    private Integer votesCount = 0;

    public Restaurant(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
