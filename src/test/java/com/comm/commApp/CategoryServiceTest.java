package com.comm.commApp;

import com.comm.commApp.Model.Category;
import com.comm.commApp.repository.CategoryRepisotory;

import com.comm.commApp.Service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepisotory categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void getAllCategoryTest() {

        Category category1 = new Category();
        category1.setName("Category 1");

        Category category2 = new Category();
        category2.setName("Category 2");

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategory();


        assertEquals(2, result.size());
        assertTrue(result.contains(category1));
        assertTrue(result.contains(category2));
    }

    @Test
    void addCategoryTest() {

        Category category = new Category();
        category.setName("New Category");

        categoryService.addCategory(category);


        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void delCategoryTest() {

        int categoryId = 1;


        categoryService.delCategory(categoryId);


        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    void getCategoryByIdTest() {

        int categoryId = 1;
        Category category = new Category();
        category.setId(categoryId);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));


        Optional<Category> result = categoryService.getCategoryById(categoryId);


        assertTrue(result.isPresent());
        assertEquals(categoryId, result.get().getId());
    }

    @Test
    void getCategoryByIdNotFoundTest() {

        int categoryId = 1;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());


        Optional<Category> result = categoryService.getCategoryById(categoryId);


        assertFalse(result.isPresent());
    }


}
