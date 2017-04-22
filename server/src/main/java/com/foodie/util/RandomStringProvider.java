package com.foodie.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by gorg on 22.04.17.
 */
@Component
public class RandomStringProvider {

    private final char[] alphabet ;

    private Random random = new Random();

    @Autowired
    public RandomStringProvider(@Value("foodie.idAlphabet") String alphabet) {
        this.alphabet = alphabet.toCharArray();
    }

    public String getRandomString(int length) {
        StringBuffer stringBuffer = new StringBuffer(length);
        for (int i = 0; i < length ; i++) {
            stringBuffer.append(this.alphabet[random.nextInt(this.alphabet.length)]);
        }
        return stringBuffer.toString();
    }
}
