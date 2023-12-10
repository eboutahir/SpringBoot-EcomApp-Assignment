package com.example.tpEcomm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
public class AuthenticationProviderConfig {

    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        return null;
    }
}

