package com.foodie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by gorg on 06.02.17.
 */
@Entity
@Data
@EqualsAndHashCode(exclude = {"meals","deliveryFee","active"})
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name ;

    private Boolean active ;

    private BigDecimal deliveryFee ;

    public Restaurant(String name, Boolean active, BigDecimal deliveryFee) {
        this.name = name;
        this.active = active;
        this.deliveryFee = deliveryFee;
    }

    protected Restaurant() {}

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "RESTAURANT_ID")
    private Set<Meal> meals ;


}
