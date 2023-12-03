package com.example.EcomApp.service;

import com.example.EcomApp.dto.CredentialsDto;
import com.example.EcomApp.dto.SigUpDto;
import com.example.EcomApp.dto.UserDto;
import com.example.EcomApp.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.CharBuffer;
import java.util.Optional;

import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepositories userRepositories;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testLogin_ValidCredentials() {
        CredentialsDto validCredentials = new CredentialsDto("username", "password");
        User mockUser = new User(1L, "John", "Doe", "username", "hashedPassword");

        when(userRepositories.findByLogin(validCredentials.getLogin())).thenReturn(Optional.of(mockUser));

        when(passwordEncoder.matches(validCredentials.getPassword().toCharArray(), mockUser.getPassword())).thenReturn(true);

        UserDto loggedInUser = userService.login(validCredentials);

        assertNotNull(loggedInUser);
        assertEquals(mockUser.getLogin(), loggedInUser.getLogin());
    }


    @Test
    public void testRegister_NewUser() {
        SigUpDto newUserDto = new SigUpDto("John", "Doe", "johndoe", "password");
        User mockUser = new User(1L, "John", "Doe", "johndoe", "hashedPassword");

        when(userRepositories.findByLogin(newUserDto.getLogin())).thenReturn(Optional.empty());
        when(userMapper.signUpToUser(newUserDto)).thenReturn(mockUser);
        when(passwordEncoder.encode(CharBuffer.wrap(newUserDto.getPassword()))).thenReturn("hashedPassword");
        when(userRepositories.save(any(User.class))).thenReturn(mockUser);
        when(userMapper.toUserDto(mockUser)).thenReturn(new UserDto(mockUser.getId(), mockUser.getFirstName(), mockUser.getLastName(), mockUser.getLogin()));

        UserDto registeredUser = userService.register(newUserDto);


        assertNotNull(registeredUser);
        assertEquals(newUserDto.getLogin(), registeredUser.getLogin());
    }

    @Test
    public void testLogin_InvalidCredentials() {
        CredentialsDto invalidCredentials = new CredentialsDto("invalidUsername", "invalidPassword");

        when(userRepositories.findByLogin(invalidCredentials.getLogin())).thenReturn(Optional.empty());

        UserDto loggedInUser = userService.login(invalidCredentials);

        assertNull(loggedInUser);
    }

    @Test
    public void testRegister_ExistingUser() {
        SigUpDto existingUserDto = new SigUpDto("John", "Doe", "existingUser", "password");
        User existingUser = new User(1L, "John", "Doe", "existingUser", "hashedPassword");

        when(userRepositories.findByLogin(existingUserDto.getLogin())).thenReturn(Optional.of(existingUser));

        UserDto registeredUser = userService.register(existingUserDto);

        assertNull(registeredUser);
    }


    @Test
    public void testLogin_NullCredentials() {
        CredentialsDto nullCredentials = new CredentialsDto(null, null);

        UserDto loggedInUser = userService.login(nullCredentials);

        assertNull(loggedInUser);
    }


}