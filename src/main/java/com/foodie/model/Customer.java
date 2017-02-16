package com.foodie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by gorg on 08.02.17.
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"active"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String login ;

    private Boolean active ;

}
