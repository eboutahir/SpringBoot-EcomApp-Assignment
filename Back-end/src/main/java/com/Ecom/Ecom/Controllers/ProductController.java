package com.Ecom.Ecom.Controllers;


import com.Ecom.Ecom.Models.Product;
import com.Ecom.Ecom.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/AllProducts")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @GetMapping("/AllCategories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/ProductByCategories/{category}")
    public List<Product> getProductsByCategories(@PathVariable("category") String cat) {
        return productService.getProductsByCategory(cat);
    }
}
