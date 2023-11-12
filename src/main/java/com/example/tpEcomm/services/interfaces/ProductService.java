package com.example.tpEcomm.services.interfaces;

import com.example.tpEcomm.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> findAll();
    Product save(Product p);
    Product update(Long id,Product p);
    Optional<Product> getById(Long id);
    void deleteProduct(Long id);
    void EnableProduct(Long id);

}
