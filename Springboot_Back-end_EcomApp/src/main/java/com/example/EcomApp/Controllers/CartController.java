package com.example.EcomApp.Controllers;

import com.example.EcomApp.model.Cart;
import com.example.EcomApp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/Add")
    public ResponseEntity<Map<String, String>> createCart(@RequestBody Cart cart) {

        cartService.AddToCart(cart);
        Map<String, String> response = new HashMap<>();
        response.put("message", "success");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}