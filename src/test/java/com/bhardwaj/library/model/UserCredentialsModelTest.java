package com.bhardwaj.library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserCredentialsModelTest {
    private UserCredentialsModel user;

    @BeforeEach
    public void setUp() {
        user = new UserCredentialsModel();
    }

    @Test
    public void testNoArgumentsConstructor() {
    	assertNotNull(user);
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getPassword());
    }

    @Test
    public void testAllArgumentsConstructor() {
        UserCredentialsModel user = new UserCredentialsModel("root", "root");
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
