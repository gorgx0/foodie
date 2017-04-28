package com.foodie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by gorg on 02.03.17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    private Long id;
    private String name ;
    private Integer votesCount = 0;

}
