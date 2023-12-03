package com.Ecomerce;

import Ecomerce.springTuto.Admin.controllers.CategoryController;
import Ecomerce.springTuto.Admin.models.Category;
import Ecomerce.springTuto.Admin.servicies.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListCategories() {
        // Mocking data
        List<Category> categories = new ArrayList<>();
        when(categoryService.getAllCategories()).thenReturn(categories);
        Model model = mock(Model.class);

        // Testing the method
        String result = categoryController.listCategories(model);

        // Assertions
        assertEquals("Admin/Category", result);

        // Utilisez les mêmes objets pour les comparaisons
        verify(model, times(1)).addAttribute(eq("categories"), anyList());
        verify(model, times(1)).addAttribute(eq("category"), any(Category.class));
        verify(model, times(1)).addAttribute(eq("action"), eq("/categories/save"));
    }


    @Test
    public void testSaveCategory() {
        // Mocking data
        Category category = new Category();

        // Testing the method
        String result = categoryController.saveCategory(category);

        // Assertions
        assertEquals("redirect:/Admin/categories", result);
        verify(categoryService, times(1)).createCategory(category);
    }

    @Test
    public void testShowEditCategoryForm() {
        // Mocking data
        Long categoryId = 1L;
        Category category = new Category();
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);
        Model model = mock(Model.class);

        // Testing the method
        String result = categoryController.showEditCategoryForm(categoryId, model);

        // Assertions
        assertEquals("addCategory", result);
        verify(model, times(1)).addAttribute("category", category);
        verify(model, times(1)).addAttribute("action", "/categories/update/" + categoryId);
    }

    @Test
    public void testUpdateCategory() {
        // Mocking data
        Long categoryId = 1L;
        Category category = new Category();

        // Testing the method
        String result = categoryController.updateCategory(categoryId, category);

        // Assertions
        assertEquals("redirect:/Admin/categories", result);
        verify(categoryService, times(1)).updateCategory(categoryId, category);
    }

    @Test
    public void testDeleteCategory() {
        // Mocking data
        Long categoryId = 1L;

        // Testing the method
        String result = categoryController.deleteCategory(categoryId);

        // Assertions
        assertEquals("redirect:/Admin/categories", result);
        verify(categoryService, times(1)).deleteCategory(categoryId);
    }
}
