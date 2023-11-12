package com.app_ecomerce.Repository;


import com.app_ecomerce.Model.Product;
import com.app_ecomerce.Model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByProductCategory(ProductCategory category);
}
