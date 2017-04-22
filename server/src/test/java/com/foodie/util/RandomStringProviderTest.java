package com.foodie.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by gorg on 22.04.17.
 */
public class RandomStringProviderTest {

    @Test
    public void getRandomString() throws Exception {
        RandomStringProvider randomStringProvider = new RandomStringProvider("a");
        String r = randomStringProvider.getRandomString(0);
        assertThat(r,notNullValue());
        assertEquals(r.length(),0);
        r = randomStringProvider.getRandomString(1);
        assertThat(r,notNullValue());
        assertEquals(r.length(),1);
        r = randomStringProvider.getRandomString(100);
        assertThat(r,notNullValue());
        assertEquals(r.length(),100);
    }

    @Test
    public void testDistributiuon() throws Exception {
        RandomStringProvider randomStringProvider = new RandomStringProvider("ab");
        String r = randomStringProvider.getRandomString(1000);
        assertThat(r,containsString("a"));
        assertThat(r,containsString("b"));
        float a = r.replace("b", "").length() / 1000.0f;
        float b = r.replace("a", "").length() / 1000.0f;
        assertTrue("Non equal distribution",Math.abs(a-b) < 0.1);
        assertTrue("Other chars in result",r.replace("a", "").replace("b", "").length() == 0);
    }
}