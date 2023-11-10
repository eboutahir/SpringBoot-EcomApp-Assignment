package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AuthenticationResponse;
import com.example.demo.dtos.AuthentificationRequest;
import com.example.demo.services.jwt.UserDatailsServiceImpl;
import com.example.demo.utils.JwtUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class AuthetificationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDatailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/authentification")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthentificationRequest authentificationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException,IOException, java.io.IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationRequest.getEmail(), authentificationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created. Register first");
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authentificationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwt);

       
    }
}
