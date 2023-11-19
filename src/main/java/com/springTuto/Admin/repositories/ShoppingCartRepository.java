package com.springTuto.Admin.repositories;

import com.springTuto.account.models.User;
import com.springTuto.Admin.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findBySessionToken(String  sessionToken);
    ShoppingCart findByUser(User user);
}