package com.Ecomerce;

import Ecomerce.springTuto.Admin.models.Category;
import Ecomerce.springTuto.Admin.repositories.CategoryRepository;
import Ecomerce.springTuto.Admin.servicies.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        // Mocking data
        List<Category> categories = new ArrayList<>();
        when(categoryRepository.findAll()).thenReturn(categories);

        // Testing the method
        List<Category> result = categoryService.getAllCategories();

        // Assertions
        assertNotNull(result);
        assertEquals(categories, result);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void testGetCategoryById() {
        // Mocking data
        Long categoryId = 1L;
        Category category = new Category();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Testing the method
        Category result = categoryService.getCategoryById(categoryId);

        // Assertions
        assertNotNull(result);
        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    public void testCreateCategory() {
        // Mocking data
        Category category = new Category();
        when(categoryRepository.save(category)).thenReturn(category);

        // Testing the method
        Category result = categoryService.createCategory(category);

        // Assertions
        assertNotNull(result);
        assertEquals(category, result);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testUpdateCategory() {
        // Mocking data
        Long categoryId = 1L;
        Category existingCategory = new Category();
        when(categoryRepository.existsById(categoryId)).thenReturn(true);
        when(categoryRepository.save(existingCategory)).thenReturn(existingCategory);

        // Testing the method
        Category result = categoryService.updateCategory(categoryId, existingCategory);

        // Assertions
        assertNotNull(result);
        assertEquals(existingCategory, result);
        verify(categoryRepository, times(1)).existsById(categoryId);
        verify(categoryRepository, times(1)).save(existingCategory);
    }

    @Test
    public void testUpdateCategoryNonExisting() {
        // Mocking data
        Long categoryId = 1L;
        Category nonExistingCategory = new Category();
        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        // Testing the method
        Category result = categoryService.updateCategory(categoryId, nonExistingCategory);

        // Assertions
        assertEquals(null, result);
        verify(categoryRepository, times(1)).existsById(categoryId);
        verify(categoryRepository, never()).save(nonExistingCategory);
    }

    @Test
    public void testDeleteCategory() {
        // Mocking data
        Long categoryId = 1L;

        // Testing the method
        categoryService.deleteCategory(categoryId);

        // Assertions
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}

