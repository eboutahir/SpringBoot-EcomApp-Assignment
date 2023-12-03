package com.example.EcomApp.service;

import com.example.EcomApp.model.Cart;
import com.example.EcomApp.model.Product;
import com.example.EcomApp.model.User;
import com.example.EcomApp.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart AddToCart(Cart cart){
        User user = cart.getUser();
        Product product = cart.getProduct();

        if (user != null && product != null) {
            cart.setUser(user);
            cart.setProduct(product);


        }
        return cartRepository.save(cart);
    }


}