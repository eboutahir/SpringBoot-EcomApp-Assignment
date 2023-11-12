package com.example.tpEcomm.controllers;

import com.example.tpEcomm.models.Product;
import com.example.tpEcomm.services.ProductServiceImp;
import com.example.tpEcomm.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Product> getAllProducts()
    {
        return service.findAll();

    }
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable("id") Long id)
    {
        return service.getById(id);
    }
    @PostMapping("/add")
    public void AddProduct(@RequestBody Product product)
    {
        service.save(product);
    }
    @DeleteMapping("/{id}")
    public void DeleteProduct(@PathVariable("id") Long id)
    {
        service.deleteProduct(id);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody Product p)
    {
        return service.update(id,p);
    }
}
