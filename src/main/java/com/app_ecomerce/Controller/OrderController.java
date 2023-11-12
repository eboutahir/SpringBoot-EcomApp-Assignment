package com.app_ecomerce.Controller;

import com.app_ecomerce.Model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.app_ecomerce.Service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    //Post
    @PostMapping("order")
    public String placeAnOrder(@RequestBody Orders orders)
    {
        return orderService.placeAnOrder(orders);
    }

    @GetMapping("Orders")
    public List<Orders> getAllOrders()
    {
        return orderService.getAllOrders();
    }
    @GetMapping("Order/{id}")
    public Optional<Orders> getOrderById(@PathVariable Integer id)
    {

        return orderService.getOrderById(id);
    }

    @DeleteMapping("order/{id}")
    public String deleteOrderById(@PathVariable Integer id)
    {
        return orderService.deleteOrderById(id);
    }
}


