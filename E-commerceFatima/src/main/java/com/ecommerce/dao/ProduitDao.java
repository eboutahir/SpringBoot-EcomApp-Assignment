package com.ecommerce.dao;

import com.ecommerce.entity.Produit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitDao extends CrudRepository<Produit, Integer> {
    public List<Produit> findAll(Pageable pageable);

    public List<Produit> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
            String key1, String key2, Pageable pageable
    );
}
