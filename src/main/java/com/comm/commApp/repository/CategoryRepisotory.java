package com.comm.commApp.repository;

import com.comm.commApp.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepisotory extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String nonexistentCategory);
}
