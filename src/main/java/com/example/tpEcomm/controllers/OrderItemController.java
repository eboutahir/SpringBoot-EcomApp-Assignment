// OrderItemController.java
package com.example.tpEcomm.controllers;

import com.example.tpEcomm.models.OrderItem;
import com.example.tpEcomm.services.interfaces.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderItem")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/all")
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<OrderItem> getOrderItemById(@PathVariable("id") Long id) {
        return orderItemService.findById(id);
    }

    @PostMapping("/add")
    public void addOrderItem(@RequestBody OrderItem orderItem) {
        orderItemService.save(orderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable("id") Long id) {
        orderItemService.deleteById(id);
    }
}
