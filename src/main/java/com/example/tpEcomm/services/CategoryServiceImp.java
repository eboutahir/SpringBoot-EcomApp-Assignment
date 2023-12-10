package com.example.tpEcomm.services;

import com.example.tpEcomm.models.Category;
import com.example.tpEcomm.repositories.CategoryRepository;
import com.example.tpEcomm.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
       // Category cat=new Category(category.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getById(Long id) {

        return categoryRepository.findById(id);
    }

    @Override
    public Category update(Category category) {
        Category catUp=new Category();
        catUp.setName(category.getName());
        catUp.set_deleted(category.is_deleted());
        return categoryRepository.save(catUp);
    }

    @Override
    public void deletedById(Long id) {
        Category catD = categoryRepository.getReferenceById(id);
        if (catD != null) {
            catD.set_deleted(true);
            categoryRepository.save(catD);
        } else {
            throw new IllegalArgumentException("Category with ID " + id + " not found");
        }
    }

    @Override
    public void enabledById(Long id) {
        Category catEnabled = categoryRepository.getReferenceById(id);
        if (catEnabled != null) {
            catEnabled.set_deleted(false);
            categoryRepository.save(catEnabled);
        } else {
            throw new IllegalArgumentException("Category with ID " + id + " not found");
        }
    }
}
