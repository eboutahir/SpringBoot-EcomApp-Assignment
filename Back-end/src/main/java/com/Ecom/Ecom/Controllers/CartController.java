package com.Ecom.Ecom.Controllers;

import com.Ecom.Ecom.Models.Cart;
import com.Ecom.Ecom.Services.CartService;
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
