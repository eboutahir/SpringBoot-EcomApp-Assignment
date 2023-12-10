// OrderController.java
package com.example.tpEcomm.controllers;

import com.example.tpEcomm.models.OrderItem;
import com.example.tpEcomm.models.Orderr;
import com.example.tpEcomm.services.interfaces.OrderItemService;
import com.example.tpEcomm.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;


    @GetMapping("/all")
    public List<Orderr> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Orderr> getOrderById(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

    @PostMapping("/add")
    public void addOrder(@RequestBody Orderr order) {
        orderService.save(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteById(id);
    }
    @PostMapping("/{orderId}/addItem")
    public void addOrderItemToOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody OrderItem orderItem) {
        Orderr order = orderService.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        orderItem.setOrder(order);
        orderItemService.save(orderItem);
    }
}
