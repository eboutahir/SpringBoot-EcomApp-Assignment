package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Produit;

public interface ProduitReposytry extends JpaRepository<Produit, Long>{

    
} 
