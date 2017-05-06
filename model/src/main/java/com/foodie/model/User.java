package com.foodie.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by gorg on 11.04.17.
 */
@Data
@RequiredArgsConstructor
public class User {

    private final String id ;
    private Group lastGroup ;

}
