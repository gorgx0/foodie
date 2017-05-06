package com.foodie.util;

import com.foodie.model.Group;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Created by gorg on 22.04.17.
 */
@Slf4j
public class UniqueKeyGeneratorImpl implements UniqueKeyGenerator {

    public final static int KEY_LENGTH = 64 ;

    @Autowired
    private RandomStringProvider randomStringProvider ;

    private final Set<String> keys ;

    @Override
    public String getUniqueKey() {
        String res = randomStringProvider.getRandomString(KEY_LENGTH);
        while (keys.contains(res)){
            LOGGER.info("Non unique key generated, next draw");
            res = randomStringProvider.getRandomString(KEY_LENGTH);
        }
        return res;
    }

    public UniqueKeyGeneratorImpl(Set<String> inSet){
        this.keys = inSet;
    }

}
