package com.comm.commApp;


import com.comm.commApp.Controller.AdminController;
import com.comm.commApp.Model.Category;
import com.comm.commApp.Service.CategoryService;
import com.comm.commApp.Service.ProductService;
import com.comm.commApp.dto.PrudoctDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adminHomeTest() {
        assertEquals("adminHome", adminController.adminHome());
    }

    @Test
    void adminCategoriesTest() {
        List<Category> categories = new ArrayList<>();
        when(categoryService.getAllCategory()).thenReturn(categories);
        assertEquals("categories", adminController.adminCategories(model));
        verify(model).addAttribute("categories", categories);
    }

    @Test
    void adminCategoriesAddTest() {
        assertEquals("categoriesAdd", adminController.adminCategoriesAdd(model));
        verify(model).addAttribute("category", new Category());
    }

    @Test
    void postadminCategoriesAddTest() {

        Category category = new Category();
        doNothing().when(categoryService).addCategory(category);
        String viewName = adminController.postadminCategoriesAdd(category);
        verify(categoryService).addCategory(category);
        assertEquals("redirect:/admin/categories", viewName);
    }




}
