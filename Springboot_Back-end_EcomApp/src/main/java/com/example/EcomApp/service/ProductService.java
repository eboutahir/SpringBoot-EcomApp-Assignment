package com.example.EcomApp.service;

import com.example.EcomApp.model.Product;
import com.example.EcomApp.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }
    @Override
    public List<String> getAllCategories() {
        return productRepository.getCategories();
    }
    @Override
    public List<Product> getProductsByCategory(String category){
        return productRepository.getProductsByCategory(category);
    }
}