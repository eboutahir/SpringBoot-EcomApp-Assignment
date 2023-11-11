package com.Ecom.Ecom.Services;


import com.Ecom.Ecom.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> getAllProducts();
    List<String> getAllCategories();
    Optional<Product> getById(Long id);
    List<Product> getProductsByCategory(String category);
}
