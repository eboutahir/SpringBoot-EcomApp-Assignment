package com.Ecom.Ecom.Services.ServicesImp;


import com.Ecom.Ecom.Models.Product;
import com.Ecom.Ecom.Repositories.ProductRepository;
import com.Ecom.Ecom.Services.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

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
