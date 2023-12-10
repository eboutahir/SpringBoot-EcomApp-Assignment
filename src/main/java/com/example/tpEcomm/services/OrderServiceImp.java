// OrderServiceImp.java
package com.example.tpEcomm.services;

import com.example.tpEcomm.models.Orderr;
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
    public List<Orderr> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Orderr save(Orderr order) {
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }

    @Override
    public Optional<Orderr> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void Delete(Long id) {
        // Assuming you have a method to soft-delete an order in your repository
        Optional<Orderr> orderOptional = orderRepository.findById(id);
        orderOptional.ifPresent(orderr -> {
            orderr.setDeleted(true); // Assuming you have a 'deleted' field in your Orderr class
            orderRepository.save(orderr);
        });
    }

    @Override
    public Orderr update(Long id, Orderr updatedOrder) {
        Optional<Orderr> existingOrderOptional = orderRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            Orderr existingOrder = existingOrderOptional.get();
            // Update fields based on your requirements
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            // Update other fields as needed
            return orderRepository.save(existingOrder);
        } else {
            throw new IllegalArgumentException("Order with ID " + id + " not found");
        }
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
