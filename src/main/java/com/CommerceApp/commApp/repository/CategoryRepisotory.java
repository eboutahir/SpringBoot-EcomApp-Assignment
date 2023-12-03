package com.CommerceApp.commApp.repository;

import com.CommerceApp.commApp.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepisotory extends JpaRepository<Category, Integer> {

}
