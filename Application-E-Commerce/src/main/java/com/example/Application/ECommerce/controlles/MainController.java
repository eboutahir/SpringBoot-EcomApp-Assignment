package com.example.Application.ECommerce.controlles;

import com.example.Application.ECommerce.models.IceCream;
import com.example.Application.ECommerce.respositories.IcecreamRepository;
import com.example.Application.ECommerce.services.IceCreamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class MainController {
    private final IceCreamService iceCreamService;

    public MainController(IceCreamService iceCreamService) {
        this.iceCreamService = iceCreamService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> getAllProducts() {
        List<IceCream> ices = iceCreamService.findAll();
        return new ResponseEntity<>(ices, HttpStatus.OK);
    }
}
