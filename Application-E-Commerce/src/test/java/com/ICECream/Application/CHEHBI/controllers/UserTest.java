package com.ICECream.Application.CHEHBI.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.ICECream.Application.CHEHBI.domain.Address;
import com.ICECream.Application.CHEHBI.domain.User;
import com.ICECream.Application.CHEHBI.domain.security.UserRole;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class UserTest {

    @Test
    void shouldReturnNullForIdWhenNotSet() {
        User user = new User();
        assertNull(user.getId());
    }

    @Test
    void shouldSetAndGetId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, Optional.ofNullable(user.getId()));
    }

    @Test
    void shouldSetAndGetUsername() {
        User user = new User();
        user.setUsername("testuser");
        assertEquals("testuser", user.getUsername());
    }

    @Test
    void shouldSetAndGetPassword() {
        User user = new User();
        user.setPassword("testpassword");
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    void shouldSetAndGetEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void shouldSetAndGetAddress() {
        User user = new User();
        Address address = new Address();
        user.setAddress(address);
        assertEquals(address, user.getAddress());
    }

    @Test
    void shouldSetAndGetUserRoles() {
        User user = new User();
        UserRole userRole = new UserRole();
        user.getUserRoles().add(userRole);
        assertTrue(user.getUserRoles().contains(userRole));
    }
}
