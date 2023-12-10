package com.example.tpEcomm.services;

import com.example.tpEcomm.models.Category;
import com.example.tpEcomm.repositories.CategoryRepository;
import com.example.tpEcomm.services.CategoryServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImpTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImp categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        when(categoryRepository.findAll()).thenReturn(categoryList);
        List<Category> result = categoryService.findAll();
        assertEquals(categoryList, result);
    }

    @Test
    void testSaveCategory() {
        Category categoryToSave = new Category();
        when(categoryRepository.save(categoryToSave)).thenReturn(categoryToSave);
        Category savedCategory = categoryService.save(categoryToSave);
        assertEquals(categoryToSave, savedCategory);
    }

    @Test
    void testFindCategoryById() {
        Long categoryId = 1L;
        Category expectedCategory = new Category();
        expectedCategory.setId(categoryId);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));
        Optional<Category> result = categoryService.getById(categoryId);
        assertTrue(result.isPresent());
        assertEquals(expectedCategory, result.get());
    }

    @Test
    void testUpdateCategory() {
        Category categoryToUpdate = new Category();
        categoryToUpdate.setId(1L);
        categoryToUpdate.setName("Updated Category");
        categoryToUpdate.set_deleted(false);

        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(categoryToUpdate);
        Category updatedCategory = categoryService.update(categoryToUpdate);
        assertEquals("Updated Category", updatedCategory.getName());
        assertFalse(updatedCategory.is_deleted());
    }

    @Test
    void testDeleteCategoryById() {
        Long categoryId = 2L;

        assertThatThrownBy(() -> categoryService.deletedById(categoryId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category with ID 2 not found");
        verify(categoryRepository, times(1)).getReferenceById(categoryId);
    }

    @Test
    void testEnableCategoryById() {
        Long categoryId = 2L;

        assertThatThrownBy(() -> categoryService.deletedById(categoryId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category with ID 2 not found");
        verify(categoryRepository, times(1)).getReferenceById(categoryId);
    }
}
