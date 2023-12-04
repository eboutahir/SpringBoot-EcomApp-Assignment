package com.example.demo.services;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.jwt.UserDatailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDatailsServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDatailsServiceImpl userDetailsService;

    @Test
    public void testLoadUserByUsername_UserFound() {
        // Mocking the UserRepository response
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("hashedPassword");

        Mockito.when(userRepository.findFirstByEmail("test@example.com")).thenReturn(mockUser);

        // Call the method to be tested
        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");

        // Verify the results
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("hashedPassword", userDetails.getPassword());
        assertEquals(new ArrayList<>(), userDetails.getAuthorities());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Mocking the UserRepository response
        Mockito.when(userRepository.findFirstByEmail(Mockito.anyString())).thenReturn(null);

        // Call the method to be tested and expect an exception
        assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("nonexistent@example.com"));
    }

    @Test
    public void testLoadUserByUsername_UserFoundNoRoles() {
        // Mocking the UserRepository response
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("hashedPassword");

        Mockito.when(userRepository.findFirstByEmail("test@example.com")).thenReturn(mockUser);

        // Call the method to be tested
        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");

        // Verify the results
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("hashedPassword", userDetails.getPassword());
        assertEquals(new ArrayList<>(), userDetails.getAuthorities());
    }

    @Test
    public void testLoadUserByUsername_UserFoundWithRoles() {
        // Mocking the UserRepository response
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("hashedPassword");
        mockUser.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));

        Mockito.when(userRepository.findFirstByEmail("test@example.com")).thenReturn(mockUser);

        // Call the method to be tested
        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");

        // Verify the results
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("hashedPassword", userDetails.getPassword());

        List<SimpleGrantedAuthority> expectedAuthorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN")
        );
        assertEquals(expectedAuthorities, new ArrayList<>(userDetails.getAuthorities()));
    }
}
