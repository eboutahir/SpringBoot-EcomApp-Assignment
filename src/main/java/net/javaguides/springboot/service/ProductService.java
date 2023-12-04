package net.javaguides.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.javaguides.springboot.model.Produit;

import net.javaguides.springboot.repository.ProduitReposytry;
import java.util.List;

@Service
public class ProductService {
    @Autowired
 private  ProduitReposytry ProduitReposytry;


 public List<Produit> getAllProducts() {
        return ProduitReposytry.findAll();
    }


    
}
