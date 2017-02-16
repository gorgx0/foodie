package com.foodie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by gorg on 08.02.17.
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"restaurant","available","price"})
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name ;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant ;

    private Boolean available ;

    private BigDecimal price ;

    public Meal(String name, Boolean available, BigDecimal price) {
        this.name = name;
        this.available = available;
        this.price = price;
    }

    public Meal() {}
}
