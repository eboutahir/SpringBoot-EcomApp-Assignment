package com.Ecomerce;


import Ecomerce.springTuto.Admin.models.CartItem;
import Ecomerce.springTuto.Admin.models.Product;
import Ecomerce.springTuto.Admin.models.ShoppingCart;
import Ecomerce.springTuto.Admin.repositories.CartItemRepository;
import Ecomerce.springTuto.Admin.repositories.ProductRepository;
import Ecomerce.springTuto.Admin.repositories.ShoppingCartRepository;
import Ecomerce.springTuto.Admin.servicies.ShopService;
import Ecomerce.springTuto.account.models.AppUser;
import Ecomerce.springTuto.account.models.User;
import Ecomerce.springTuto.account.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.*;

public class ShopServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private ShopService shopService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddItem() {
        // Mock data
        String userEmail = "test@example.com";
        AppUser appUser = new AppUser();
        appUser.setUsername(userEmail);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(new User());
        shoppingCart.setItems(new HashSet<>());

        Long productId = 1L;
        Integer quantity = 2;

        Product product = new Product();
        product.setId(productId);

        when(accountRepository.findByEmail(userEmail)).thenReturn(new User());
        when(shoppingCartRepository.findByUser(any())).thenReturn(shoppingCart);
        when(productRepository.getById(productId)).thenReturn(product);
        when(cartItemRepository.save(any())).thenReturn(new CartItem(product, quantity));
        when(shoppingCartRepository.save(any())).thenReturn(shoppingCart);

        // Test the method
        shopService.addItem(appUser, productId, quantity);

        // Verify interactions
        verify(accountRepository, times(1)).findByEmail(userEmail);
        verify(shoppingCartRepository, times(1)).findByUser(any());
        verify(productRepository, times(1)).getById(productId);
        verify(cartItemRepository, times(1)).save(any());
        verify(shoppingCartRepository, times(1)).save(any());
    }

    @Test
    public void testRemoveItem() {
        // Mock data
        AppUser appUser = new AppUser();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(new User());
        shoppingCart.setItems(new HashSet<>());

        Long itemId = 1L;

        when(shoppingCartRepository.findByUser(any())).thenReturn(shoppingCart);
        doNothing().when(cartItemRepository).deleteById(any());

        // Test the method
        ShoppingCart result = shopService.removeItem(appUser, itemId);

        // Verify interactions
        verify(shoppingCartRepository, times(1)).findByUser(any());
        verify(cartItemRepository, times(1)).deleteById(any());

        // Check if result is not null before accessing its items
        assertNotNull(result);
        assertNotNull(result.getItems());

        // ... rest of your assertions
    }

}
