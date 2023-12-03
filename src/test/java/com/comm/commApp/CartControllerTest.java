package com.comm.commApp;

import com.comm.commApp.Controller.CartController;
import com.comm.commApp.Model.Product;
import com.comm.commApp.Service.ProductService;
import com.comm.commApp.global.GlobalData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addToCartTest() {
        Long productId = 1L;
        when(productService.getProduct(productId)).thenReturn(Optional.of(new Product()));

        String viewName = cartController.addToCart(productId);

        verify(productService).getProduct(productId);
        assertEquals("redirect:/shop", viewName);
        assertEquals(1, GlobalData.cart.size());
    }

    @Test
    void cartGetTest() {

        List<Product> cartItems = new ArrayList<>();
        cartItems.add(new Product());
        cartItems.add(new Product());

        GlobalData.cart = cartItems;

        String viewName = cartController.cartGet(model);

        verify(model).addAttribute("cartCount", cartItems.size());
        verify(model).addAttribute("total", cartItems.stream().mapToDouble(Product::getPrice).sum());
        verify(model).addAttribute("cart", cartItems);
        assertEquals("cart", viewName);
    }

    @Test
    void cartItemRemoveTest() {

        List<Product> cartItems = new ArrayList<>();
        cartItems.add(new Product());
        cartItems.add(new Product());

        GlobalData.cart = cartItems;

        int indexToRemove = 1;
        String viewName = cartController.cartItemRemove(indexToRemove);

        verify(model, never()).addAttribute(anyString(), any());
        assertEquals("redirect:/cart", viewName);
        assertEquals(1, GlobalData.cart.size());
    }

    @Test
    void checkoutTest() {

        List<Product> cartItems = new ArrayList<>();
        cartItems.add(new Product());
        cartItems.add(new Product());

        GlobalData.cart = cartItems;

        String viewName = cartController.checkout(model);

        verify(model).addAttribute("total", cartItems.stream().mapToDouble(Product::getPrice).sum());
        assertEquals("checkout", viewName);
    }



}
