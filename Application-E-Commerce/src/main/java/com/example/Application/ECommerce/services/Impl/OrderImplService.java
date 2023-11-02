package com.example.Application.ECommerce.services.Impl;

import com.example.Application.ECommerce.models.Order;
import com.example.Application.ECommerce.models.User;
import com.example.Application.ECommerce.respositories.OrderRepository;
import com.example.Application.ECommerce.services.OrderService;

import java.util.List;

public class OrderImplService implements OrderService {
    public OrderRepository orderRepository;
    @Override
    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderByUser(User user) {
        return orderRepository.findOrderByUser(user);
    }
}
