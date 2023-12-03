package com.example.EcomApp.service;

import com.example.EcomApp.model.Cart;
import com.example.EcomApp.model.Product;
import com.example.EcomApp.model.User;
import com.example.EcomApp.repositories.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testGetCartById() {
        Long cartId = 1L;
        Cart mockCart = new Cart(cartId, new Date(), 2L, new Product(), new User());

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(mockCart));

        Optional<Cart> retrievedCart = cartService.getCartById(cartId);

        assertTrue(retrievedCart.isPresent());
        assertEquals(2L, retrievedCart.get().getQuantity());
    }


    @Test
    public void testRemoveProductFromCart() {

        Long cartId = 1L;
        Cart existingCart = new Cart(cartId, new Date(), 2L, new Product(), new User());

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);

        boolean isProductRemoved = cartService.removeProductFromCart(cartId);

        assertTrue(isProductRemoved);

    }

    @Test
    public void testAddProductToCart() {
        Cart existingCart = new Cart(1L, new Date(), 2L, new Product(), new User());

        when(cartRepository.findById(existingCart.getId())).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);

        Product productToAdd = new Product();

        boolean isProductAdded = cartService.addProductToCart(existingCart.getId(), productToAdd);


        assertTrue(isProductAdded);
    }

    @Test
    public void testEmptyCartRetrieval() {
        Long cartId = 1L;

        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        Optional<Cart> retrievedCart = cartService.getCartById(cartId);

        assertFalse(retrievedCart.isPresent());
    }


    @Test
    public void testMaximumQuantity() {
        Long cartId = 1L;
        Cart existingCart = new Cart(cartId, new Date(), Integer.MAX_VALUE, new Product(), new User());

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(existingCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(existingCart);

        Product productToAdd = new Product();

        boolean isProductAdded = cartService.addProductToCart(cartId, productToAdd);

        assertTrue(isProductAdded);
    }


    @Test
    public void testInvalidCartId() {
        Long invalidCartId = 999L;

        when(cartRepository.findById(invalidCartId)).thenReturn(Optional.empty());

        boolean isProductRemoved = cartService.removeProductFromCart(invalidCartId);

        assertFalse(isProductRemoved);
    }


}
