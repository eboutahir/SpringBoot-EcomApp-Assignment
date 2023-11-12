package TP4.EcomApp.service;

import TP4.EcomApp.entity.Product;
import TP4.EcomApp.entity.User;
import TP4.EcomApp.Repositories.ProductRepository;
import TP4.EcomApp.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    List<Product> productList = null;

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;   


    public List<Product> getProducts() {
    	productList=productRepository.findAll();
    	System.out.println("XXXX"+productList.size());
        return productList;
    }

    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }

    public String addUser(User userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }
}
