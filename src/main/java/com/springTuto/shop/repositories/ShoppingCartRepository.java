package com.springTuto.shop.repositories;

import com.springTuto.shop.models.ShoppingCart;
import com.springTuto.account.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findBySessionToken(String  sessionToken);
    ShoppingCart findByUser(User user);
}