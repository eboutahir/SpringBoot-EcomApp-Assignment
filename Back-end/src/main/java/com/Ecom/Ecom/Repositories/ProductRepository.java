package com.Ecom.Ecom.Repositories;


import com.Ecom.Ecom.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT DISTINCT p.category FROM Product p")
    List<String> getCategories();

    @Query(value = "SELECT * FROM Product p WHERE p.category= :category", nativeQuery = true)
    List<Product> getProductsByCategory(@Param("category") String category);
}
