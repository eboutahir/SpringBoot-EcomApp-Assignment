package com.example.tpEcomm.services.interfaces;

import com.example.tpEcomm.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
    Optional<Category> getById(Long id);
    Category update(Category category);
    void deletedById(Long id);
    void enabledById(Long id);
}



