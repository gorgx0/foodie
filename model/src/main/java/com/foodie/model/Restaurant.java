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

    private String name ;
    private Boolean active ;
    private BigDecimal deliveryFee ;

}
