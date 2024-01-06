package com.example.APPEcom.Produits.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.APPEcom.Comptes.Models.AppUser;
import com.example.APPEcom.Comptes.Models.User;
import com.example.APPEcom.Produits.Models.CartItem;
import com.example.APPEcom.Produits.Models.Product;
import com.example.APPEcom.Produits.Models.ShoppingCart;
import com.example.APPEcom.Produits.Repositories.CartItemRepository;
import com.example.APPEcom.Produits.Repositories.ProductRepository;
import com.example.APPEcom.Produits.Repositories.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

public class ShopServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private User account;

    @InjectMocks
    private ShopService shopService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddItem() {
        String userEmail = "test@example.com";
        AppUser appUser = new AppUser();
        appUser.setUsername(userEmail);

        Product product = new Product();
        product.setId(1L);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(account);
        shoppingCart.setItems(new HashSet<>());

        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(userEmail);
        when(accountRepository.findByEmail(userEmail)).thenReturn(account);
        when(shoppingCartRepository.findByUser(account)).thenReturn(shoppingCart);
        when(productRepository.getById(1L)).thenReturn(product);

        shopService.addItem(appUser, 1L, 2);

        verify(shoppingCartRepository, times(1)).save(shoppingCart);
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }
}
