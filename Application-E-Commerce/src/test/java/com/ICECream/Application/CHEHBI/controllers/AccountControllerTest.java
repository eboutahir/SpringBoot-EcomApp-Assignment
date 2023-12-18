package com.ICECream.Application.CHEHBI.controllers;

import com.ICECream.Application.CHEHBI.controller.AccountController;
import com.ICECream.Application.CHEHBI.domain.Order;
import com.ICECream.Application.CHEHBI.domain.User;
import com.ICECream.Application.CHEHBI.service.OrderService;
import com.ICECream.Application.CHEHBI.service.UserService;
import com.ICECream.Application.CHEHBI.service.impl.UserSecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private UserService userService;

    @Mock
    private UserSecurityService userSecurityService;

    @Mock
    private OrderService orderService;

    @Mock
    private Authentication authentication;

    @Mock
    private Principal principal;

    @Mock
    private Model model;

    @Test
    public void shouldReturnMyProfileView() {

        User user = new User();
        when(authentication.getPrincipal()).thenReturn(user);

        String result = accountController.myProfile(model, authentication);

        assertEquals("myProfile", result);
        verify(model).addAttribute("user", user);
    }

    @Test
    public void shouldReturnMyOrdersView() {

        User user = new User();
        when(authentication.getPrincipal()).thenReturn(user);
        List<Order> orders = Collections.emptyList();
        when(orderService.findByUser(user)).thenReturn(orders);

        String result = accountController.myOrders(model, authentication);

        assertEquals("myOrders", result);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("orders", orders);
    }
}
