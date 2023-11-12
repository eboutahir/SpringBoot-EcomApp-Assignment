/*package com.example.tpEcomm.services;

import com.example.tpEcomm.models.Order;
import com.example.tpEcomm.models.User;
import com.example.tpEcomm.repositories.OrderRepository;
import com.example.tpEcomm.repositories.UserRepository;
import com.example.tpEcomm.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }



    @Override
    public void Delete(Long id) {

    }

    @Override
    public Order update(Long id, Order order) {
        return null;
    }
}
*/