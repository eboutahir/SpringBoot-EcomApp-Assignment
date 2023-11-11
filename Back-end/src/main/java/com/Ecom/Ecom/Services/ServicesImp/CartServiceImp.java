package com.Ecom.Ecom.Services.ServicesImp;

import com.Ecom.Ecom.Models.Cart;
import com.Ecom.Ecom.Models.Product;
import com.Ecom.Ecom.Models.Users;
import com.Ecom.Ecom.Repositories.CartRepository;
import com.Ecom.Ecom.Services.CartService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class CartServiceImp  implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart AddToCart(Cart cart){
        Users user = cart.getUser();
        Product product = cart.getProduct();

        if (user != null && product != null) {
            cart.setUser(user);
            cart.setProduct(product);


    }
        return cartRepository.save(cart);
    }

}
