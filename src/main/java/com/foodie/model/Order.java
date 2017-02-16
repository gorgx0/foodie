package com.foodie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gorg on 08.02.17.
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"status"})
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private Date date ;

    @Enumerated(EnumType.STRING)
    private OrderStatus status ;

    public static enum OrderStatus  {
        OPENED,
        CLOSED,
        PAID,
        DELIVERED;
    }
}
