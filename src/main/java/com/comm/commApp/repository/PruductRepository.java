package com.comm.commApp.repository;


import com.comm.commApp.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PruductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategoryId(int id);
}
