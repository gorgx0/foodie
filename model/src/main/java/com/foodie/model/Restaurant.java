package com.foodie.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by gorg on 02.03.17.
 */
@Data
public class Restaurant {

    private String name ;
    private Boolean active ;
    private BigDecimal deliveryFee ;

}
