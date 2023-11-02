package com.example.Application.ECommerce.respositories;

import com.example.Application.ECommerce.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
