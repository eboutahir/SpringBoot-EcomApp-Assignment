package com.example.EcomApp.Controllers;

import com.example.EcomApp.dto.CredentialsDto;
import com.example.EcomApp.dto.SigUpDto;
import com.example.EcomApp.dto.UserDto;
import com.example.EcomApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AuthController {


    private  UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto ){
        UserDto user = userService.login(credentialsDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SigUpDto sigUpDto ){
        UserDto user = userService.register(sigUpDto);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }


}
