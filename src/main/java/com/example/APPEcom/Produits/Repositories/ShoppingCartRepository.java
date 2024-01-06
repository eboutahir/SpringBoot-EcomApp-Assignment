package com.example.APPEcom.Produits.Repositories;

import com.example.APPEcom.Comptes.Models.User;
import com.example.APPEcom.Produits.Models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findBySessionToken(String  sessionToken);
    ShoppingCart findByUser(User user);
}