package com.CommerceApp.commApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;

@SpringBootApplication
public class CommAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommAppApplication.class, args);
	}

}
