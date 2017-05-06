package com.foodie.util;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by gorg on 06.05.17.
 */
public class InvitationNotFoundException extends RuntimeException {
    public InvitationNotFoundException(String id) {
        super("Infitation not found for id="+id);
    }
}
