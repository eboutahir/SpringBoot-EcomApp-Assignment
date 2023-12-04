package com.example.demo.controllers;

import com.example.demo.controllers.SignupUserController;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.UserDTO;
import com.example.demo.services.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

public class SignupUserControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private SignupUserController signupUserController;

    @Test
    public void testCreateUser_Success() {
        // Mocking successful user creation
        UserDTO mockUser = new UserDTO();
        Mockito.when(authService.createUser(any(SignupRequest.class))).thenReturn(mockUser);

        // Call the method to be tested
        SignupRequest signupRequest = new SignupRequest();
        ResponseEntity<?> responseEntity = signupUserController.createUser(signupRequest);

        // Verify the results
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockUser, responseEntity.getBody());
    }

    @Test
    public void testCreateUser_Failure() {
        // Mocking user creation failure
        Mockito.when(authService.createUser(any(SignupRequest.class))).thenReturn(null);

        // Call the method to be tested
        SignupRequest signupRequest = new SignupRequest();
        ResponseEntity<?> responseEntity = signupUserController.createUser(signupRequest);

        // Verify the results
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User not created, come again later!", responseEntity.getBody());
    }

    @Test
    public void testCreateUser_InvalidInput() {
        // Mocking invalid input scenario
        Mockito.when(authService.createUser(any(SignupRequest.class))).thenThrow(IllegalArgumentException.class);

        // Call the method to be tested
        SignupRequest signupRequest = new SignupRequest();
        ResponseEntity<?> responseEntity = signupUserController.createUser(signupRequest);

        // Verify the results
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateUser_ExistingUser() {
        // Mocking scenario where the user already exists
        Mockito.when(authService.createUser(any(SignupRequest.class))).thenThrow(IllegalStateException.class);

        // Call the method to be tested
        SignupRequest signupRequest = new SignupRequest();
        ResponseEntity<?> responseEntity = signupUserController.createUser(signupRequest);

        // Verify the results
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User already exists!", responseEntity.getBody());
    }

    @Test
    public void testCreateUser_NullUser() {
        // Mocking scenario where the created user is null
        Mockito.when(authService.createUser(any(SignupRequest.class))).thenReturn(null);

        // Call the method to be tested
        SignupRequest signupRequest = new SignupRequest();
        ResponseEntity<?> responseEntity = signupUserController.createUser(signupRequest);

        // Verify the results
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateUser_NullRequest() {
        // Mocking scenario with a null signup request
        ResponseEntity<?> responseEntity = signupUserController.createUser(null);

        // Verify the results
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
