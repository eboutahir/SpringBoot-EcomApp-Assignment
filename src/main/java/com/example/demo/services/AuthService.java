package com.example.demo.services;

import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.UserDTO;

public interface AuthService {

    UserDTO createUser(SignupRequest signupRequest);
    
}
