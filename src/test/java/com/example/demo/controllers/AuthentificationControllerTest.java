package com.example.demo.controllers;
import com.example.demo.controllers.AuthetificationController;
import com.example.demo.dtos.AuthenticationResponse;
import com.example.demo.dtos.AuthentificationRequest;
import com.example.demo.services.jwt.UserDatailsServiceImpl;
import com.example.demo.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletResponse;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthetificationControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDatailsServiceImpl userDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthetificationController authController;

    @Test
    public void testCreateAuthenticationToken_Success() {
        // Mocking successful authentication
        Mockito.doNothing().when(authenticationManager)
                .authenticate(new UsernamePasswordAuthenticationToken("test@example.com", "password"));

        // Mocking user details retrieval
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(userDetails);

        // Mocking JWT generation
        Mockito.when(jwtUtil.generateToken("test@example.com")).thenReturn("mockedJwt");

        // Call the method to be tested
        AuthentificationRequest authRequest = new AuthentificationRequest("test@example.com", "password");
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AuthenticationResponse authResponse = authController.createAuthenticationToken(authRequest, response);

        // Verify the results
        assertNotNull(authResponse);
        assertEquals("mockedJwt", authResponse.getJwt());
    }
    }

    @Test
    public void testCreateAuthenticationToken_BadCredentials() {
        // Mocking bad credentials exception
        doThrow(BadCredentialsException.class)
                .when(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken("test@example.com", "wrongPassword"));

        // Call the method to be tested and expect an exception
        AuthentificationRequest authRequest = new AuthentificationRequest("test@example.com", "wrongPassword");
        assertThrows(BadCredentialsException.class,
                () -> authController.createAuthenticationToken(authRequest, mock(HttpServletResponse.class)));
    }

    @Test
    public void testCreateAuthenticationToken_UserNotFound() {
        // Mocking user not found exception
        doThrow(UsernameNotFoundException.class)
                .when(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken("nonexistent@example.com", "password"));

        // Call the method to be tested and expect an exception
        AuthentificationRequest authRequest = new AuthentificationRequest("nonexistent@example.com", "password");
        assertThrows(UsernameNotFoundException.class,
                () -> authController.createAuthenticationToken(authRequest, mock(HttpServletResponse.class)));
    }

    @Test
    public void testCreateAuthenticationToken_NullJwt() {
        // Mocking successful authentication
        doNothing().when(authenticationManager)
                .authenticate(new UsernamePasswordAuthenticationToken("test@example.com", "password"));

        // Mocking user details retrieval
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(userDetails);

        // Mocking null JWT generation
        when(jwtUtil.generateToken("test@example.com")).thenReturn(null);

        // Call the method to be tested
        AuthentificationRequest authRequest = new AuthentificationRequest("test@example.com", "password");
        AuthenticationResponse response = authController.createAuthenticationToken(authRequest, mock(HttpServletResponse.class));

        // Verify the result
        assertNotNull(response);
        assertNull(response.getJwt());
    }
}
