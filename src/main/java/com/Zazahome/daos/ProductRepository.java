package com.Zazahome.daos;

import java.util.List;

import com.Zazahome.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Zazahome.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query(value = "select * from products", nativeQuery = true)
	public List<Product> getProducts();
	
	@Query(value = "select * from products where product_seller_id = ?", nativeQuery = true)
	public List<Product> getSellerAllProducts(@Param("id") int id);

    Object getProductsByCategory(Category category);
}
