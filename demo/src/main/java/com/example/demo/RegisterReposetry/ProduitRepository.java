package com.example.demo.RegisterReposetry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.RegisterModel.produit;

public interface ProduitRepository extends JpaRepository<produit,Long>{

    
}  


