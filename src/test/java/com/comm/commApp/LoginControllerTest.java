package com.comm.commApp;

import com.comm.commApp.Controller.LoginController;
import com.comm.commApp.global.GlobalData;
import com.comm.commApp.repository.RoleRepository;
import com.comm.commApp.repository.UserReository;
import com.comm.commApp.Model.Role;
import com.comm.commApp.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UserReository userReository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginTest() {
        String viewName = loginController.login();
        assertEquals("login", viewName);
        assertEquals(0, GlobalData.cart.size());
    }

    @Test
    void registerGetTest() {
        String viewName = loginController.registerGet();
        assertEquals("register", viewName);
    }

    @Test
    void registerPostTest() throws ServletException {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        Role role = new Role();
        role.setId(2);
        when(roleRepository.findById(2)).thenReturn(Optional.of(role));

        String encodedPassword = "encodedPassword";
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);

        String viewName = loginController.registerPost(user, request);

        verify(userReository).save(user);
        verify(request).login(user.getEmail(), user.getPassword());

        assertEquals("redirect:/", viewName);
    }



}
