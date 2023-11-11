package com.Ecom.Ecom.Services;

import com.Ecom.Ecom.Models.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart AddToCart(Cart cart);
}
