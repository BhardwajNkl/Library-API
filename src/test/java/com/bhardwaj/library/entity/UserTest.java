package com.bhardwaj.library.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testNoArgumentsConstructor() {
    	assertNotNull(user);
        assertEquals(0, user.id);
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getPassword());
    }

    @Test
    public void testAllArgumentsConstructor() {
        User user = new User("root", "root");
        assertNotNull(user);
        assertEquals("root", user.getUsername());
        assertEquals("root", user.getPassword());
    }

    @Test
    public void testGettersAndSetters() {
        user.setUsername("root");
        user.setPassword("root");

        assertEquals("root", user.getUsername());
        assertEquals("root", user.getPassword());
    }
}
