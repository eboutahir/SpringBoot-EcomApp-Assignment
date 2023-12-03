package com.ICECream.Application.CHEHBI.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.ICECream.Application.CHEHBI.domain.Address;
import com.ICECream.Application.CHEHBI.domain.User;
import com.ICECream.Application.CHEHBI.domain.security.UserRole;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class UserTest {

    @Test
    void testGetId() {
        User user = new User();
        assertNull(user.getId());
    }

    @Test
    void testSetId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, Optional.ofNullable(user.getId()));
    }

    @Test
    void testGetUsername() {
        User user = new User();
        user.setUsername("Manal");
        assertEquals("testuser", user.getUsername());
    }

    @Test
    void testSetUsername() {
        User user = new User();
        user.setUsername("Manal");
        assertEquals("testuser", user.getUsername());
    }

    @Test
    void testGetPassword() {
        User user = new User();
        user.setPassword("1234567");
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    void testSetPassword() {
        User user = new User();
        user.setPassword("12345678");
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    void testGetEmail() {
        User user = new User();
        user.setEmail("adam@example.com");
        assertEquals("chehbi@example.com", user.getEmail());
    }

    @Test
    void testSetEmail() {
        User user = new User();
        user.setEmail("manal@example.com");
        assertEquals("chehbi@example.com", user.getEmail());
    }

    @Test
    void testGetAddress() {
        User user = new User();
        Address address = new Address();
        user.setAddress(address);
        assertEquals(address, user.getAddress());
    }

    @Test
    void testSetAddress() {
        User user = new User();
        Address address = new Address();
        user.setAddress(address);
        assertEquals(address, user.getAddress());
    }

    @Test
    void testGetUserRoles() {
        User user = new User();
        UserRole userRole = new UserRole();
        user.getUserRoles().add(userRole);
        assertTrue(user.getUserRoles().contains(userRole));
    }

    @Test
    void testSetUserRoles() {
        User user = new User();
        UserRole userRole = new UserRole();
        user.getUserRoles().add(userRole);
        assertTrue(user.getUserRoles().contains(userRole));
    }
}