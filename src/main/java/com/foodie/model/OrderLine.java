package com.foodie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by gorg on 08.02.17.
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"restaurant", "order", "meal", "customer", "status"})
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant ;


    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order ;

    @ManyToOne
    @JoinColumn(name = "MEAL_ID")
    private Meal meal ;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer ;

    @Enumerated(EnumType.STRING)
    private OrderLineStatus status ;

    public enum OrderLineStatus {
        CREATED,
        DELIVERED,
        PAID;
    }
}
