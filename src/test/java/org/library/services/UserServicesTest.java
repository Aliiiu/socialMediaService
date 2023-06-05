package org.library.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.library.models.User;

import static org.junit.jupiter.api.Assertions.*;

class UserServicesTest {
    private static UserServices userServices;
    private static User user;
    @BeforeEach
    void setUp() {
        userServices = new UserServices("socials-pu-test");
        user = new User("Aliu", "12345");
    }

    @AfterEach
    void tearDown() {
        userServices.close();
    }

    @Test
    void testRegisterUser() {
        user = userServices.registerUser(user.getUsername(), user.getPassword());

        assertNotNull(user.getUserId());
    }

    @Test
    void testFindByUsername() {
        userServices.registerUser("Aliu", "12345");

        user = userServices.findByUsername("Aliu");

        assertNotNull(user);
        assertNotNull(user.getUserId());
    }

    @Test
    void testSignIn() {
        userServices.registerUser("Aliu", "12345");
        user = userServices.signIn("Aliu", "12345");

        assertNotNull(user);
        assertEquals("Aliu", user.getUsername());
    }

    @Test
    void testInvalidUserName(){
        userServices.registerUser("Aliu", "12345");
        user = userServices.signIn("Alius", "12345");

        assertNull(user);
    }

}