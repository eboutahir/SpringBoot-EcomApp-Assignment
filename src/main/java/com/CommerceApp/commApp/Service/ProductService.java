package com.CommerceApp.commApp.Service;

import com.CommerceApp.commApp.Model.Category;
import com.CommerceApp.commApp.Model.Product;
import com.CommerceApp.commApp.repository.PruductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    PruductRepository pruductRepository;
    public List<Product> getAllProduct()
    {
        return pruductRepository.findAll();
    }
    public void addProduct(Product product)
    {
        pruductRepository.save(product);
    }
    public void delProduct(Long id)
    {
        pruductRepository.deleteById(id);
    }
    public Optional<Product> getProduct(Long id)
    {
        return pruductRepository.findById(id);
    }

    public List<Product> getAllProductsByCategory(int id)
    {
       return pruductRepository.findAllByCategoryId(id);
    }
}
