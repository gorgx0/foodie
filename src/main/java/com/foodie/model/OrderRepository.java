package com.foodie.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by gorg on 13.02.17.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
}
