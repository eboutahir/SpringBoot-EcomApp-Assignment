package com.example.Application.ECommerce.services;

import com.example.Application.ECommerce.models.Order;
import com.example.Application.ECommerce.models.User;

import java.util.List;

public interface OrderService {
     List<Order> findOrders();
     Order save(Order order);
     List<Order> findOrderByUser(User user);

}
