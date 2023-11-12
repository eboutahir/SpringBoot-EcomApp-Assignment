package com.example.EcomApp.service;

import com.example.EcomApp.dto.CredentialsDto;
import com.example.EcomApp.dto.SigUpDto;
import com.example.EcomApp.dto.UserDto;
import com.example.EcomApp.exceptions.AppException;
import com.example.EcomApp.mappers.UserMapper;
import com.example.EcomApp.model.User;
import com.example.EcomApp.repositories.UserRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepositories userRepositories;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepositories.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknow user", HttpStatus.NOT_FOUND));


        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()),
                user.getPassword())){
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SigUpDto userDto) {
        Optional<User> optionalUser = userRepositories.findByLogin(userDto.login());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        User savedUser = userRepositories.save(user);

        return userMapper.toUserDto(savedUser);

    }



}
