package com.foodie.util;

import com.foodie.Main;
import com.foodie.model.Group;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by gorg on 22.04.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UniqueKeyGeneratorTest {


    @Autowired
    Map<String, Group> groups = new HashMap<>();

    @MockBean
    RandomStringProvider randomStringProvider;

    @Autowired
    UniqueKeyGeneratorImpl uniqueKeyGeneratorImpl ;

    @Before
    public void setUp() throws Exception {
        groups.clear();
    }

    @Test
    public void getValue() throws Exception {
        when(randomStringProvider.getRandomString(UniqueKeyGeneratorImpl.KEY_LENGTH)).thenReturn("0123456789012345678901234567890123456789012345678901234567890123");
        String v = uniqueKeyGeneratorImpl.getUniqueKey();
        assertThat(v, notNullValue());
        assertEquals(UniqueKeyGeneratorImpl.KEY_LENGTH, v.length());
    }

    @Test(timeout = 10000)
    public void checkIfTheKeyIsUnique() throws Exception {
        Group g = mock(Group.class);
        String key1 = "aaaaa";
        String key2 = "bbbbb";
        groups.put(key1, g);
        when(randomStringProvider.getRandomString(UniqueKeyGeneratorImpl.KEY_LENGTH))
                .thenReturn("aaaaa")
                .thenReturn("bbbbb")
                .thenThrow(new IllegalStateException());
        String uniqueKey = uniqueKeyGeneratorImpl.getUniqueKey();
        assertTrue(groups.keySet().contains(key1));
        assertEquals("bbbbb",uniqueKey);
    }

    @TestConfiguration
    class TestConfig {

        @Bean
        UniqueKeyGeneratorImpl uniqueKeyGenerator() {
            return new UniqueKeyGeneratorImpl(groups);
        }



    }

}