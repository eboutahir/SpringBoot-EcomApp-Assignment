package com.springTuto.Admin.servicies;
import com.springTuto.Admin.models.OrderItem;
import com.springTuto.Admin.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        // Ajoutez des vérifications/logiques supplémentaires si nécessaire
        return orderItemRepository.save(orderItem);
    }

    // Autres méthodes de service en fonction des besoins de votre application
}
