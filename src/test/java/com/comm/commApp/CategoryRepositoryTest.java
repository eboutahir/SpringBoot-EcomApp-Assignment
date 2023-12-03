package com.comm.commApp;

import com.comm.commApp.Model.Category;

import com.comm.commApp.repository.CategoryRepisotory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepisotory categoryRepisotory;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveCategoryTest() {

        Category category = new Category();
        category.setName("Test Category");


        Category savedCategory = categoryRepisotory.save(category);


        assertEquals(category.getName(), savedCategory.getName());
        assertTrue(savedCategory.getId() > 0);


        entityManager.flush();
        entityManager.clear();

        Optional<Category> retrievedCategory = categoryRepisotory.findById(savedCategory.getId());
        assertTrue(retrievedCategory.isPresent());
        assertEquals(savedCategory.getName(), retrievedCategory.get().getName());
    }

    @Test
    void findByNameTest() {

        Category category = new Category();
        category.setName("Test Category");
        categoryRepisotory.save(category);


        Optional<Category> foundCategory = categoryRepisotory.findByName("Test Category");


        assertTrue(foundCategory.isPresent());
        assertEquals(category.getName(), foundCategory.get().getName());
    }

    @Test
    void findByNameNotFoundTest() {

        Optional<Category> foundCategory = categoryRepisotory.findByName("Nonexistent Category");


        assertFalse(foundCategory.isPresent());
    }

    @Test
    void updateCategoryTest() {

        Category category = new Category();
        category.setName("Original Category");
        CategoryRepisotory categoryRepository = null;
        categoryRepository.save(category);


        Category retrievedCategory = categoryRepository.findByName("Original Category")
                .orElseThrow(() -> new RuntimeException("Category not found"));
        retrievedCategory.setName("Updated Category");
        Category updatedCategory = categoryRepository.save(retrievedCategory);


        assertEquals("Updated Category", updatedCategory.getName());


        entityManager.flush();
        entityManager.clear();

        Optional<Category> foundCategory = categoryRepository.findById(updatedCategory.getId());
        assertTrue(foundCategory.isPresent());
        assertEquals("Updated Category", foundCategory.get().getName());
    }

    @Test
    void deleteCategoryTest() {

        Category category = new Category();
        category.setName("Category to Delete");
        categoryRepisotory.save(category);


        categoryRepisotory.delete(category);


        assertFalse(categoryRepisotory.existsById(category.getId()));
    }

    @Test
    void getAllCategoriesTest() {

        Category category1 = new Category();
        category1.setName("Category 1");
        categoryRepisotory.save(category1);

        Category category2 = new Category();
        category2.setName("Category 2");
        categoryRepisotory.save(category2);


        List<Category> allCategories = categoryRepisotory.findAll();


        assertEquals(2, allCategories.size());
        assertTrue(allCategories.stream().anyMatch(c -> c.getName().equals("Category 1")));
        assertTrue(allCategories.stream().anyMatch(c -> c.getName().equals("Category 2")));
    }


}
