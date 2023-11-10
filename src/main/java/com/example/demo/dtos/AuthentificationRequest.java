package com.example.demo.dtos;

import lombok.Data;

@Data
public class AuthentificationRequest {
    
    private String email;

    private String password;
}
