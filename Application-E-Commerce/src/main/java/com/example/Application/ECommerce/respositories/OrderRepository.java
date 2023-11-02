package com.example.Application.ECommerce.respositories;

import com.example.Application.ECommerce.models.Order;
import com.example.Application.ECommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByUser(User user);
}
