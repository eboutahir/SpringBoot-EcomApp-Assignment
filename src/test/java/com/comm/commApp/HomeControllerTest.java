package com.comm.commApp;

import com.comm.commApp.Controller.HomeController;
import com.comm.commApp.Model.Product;
import com.comm.commApp.Service.CategoryService;
import com.comm.commApp.Service.ProductService;
import com.comm.commApp.global.GlobalData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

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
    void homeTest() {
        String viewName = homeController.home(model);
        assertEquals("index", viewName);
    }

    @Test
    void shopTest() {
        when(categoryService.getAllCategory()).thenReturn(new ArrayList<>());
        when(productService.getAllProduct()).thenReturn(new ArrayList<>());

        String viewName = homeController.shop(model);

        verify(model).addAttribute("categories", categoryService.getAllCategory());
        verify(model).addAttribute("products", productService.getAllProduct());
        verify(model).addAttribute("cartCount", GlobalData.cart.size());

        assertEquals("shop", viewName);
    }

    @Test
    void shopByCategoryTest() {
        int categoryId = 1;
        when(categoryService.getAllCategory()).thenReturn(new ArrayList<>());
        when(productService.getAllProductsByCategory(categoryId)).thenReturn(new ArrayList<>());

        String viewName = homeController.shopByCategory(model, categoryId);

        verify(model).addAttribute("categories", categoryService.getAllCategory());
        verify(model).addAttribute("products", productService.getAllProductsByCategory(categoryId));

        assertEquals("shop", viewName);
    }

    @Test
    void viewProductTest() {
        Long productId = 1L;
        when(productService.getProduct(productId)).thenReturn(java.util.Optional.of(new Product()));

        String viewName = homeController.viewProduct(model, productId);

        verify(model).addAttribute("product", productService.getProduct(productId).get());

        assertEquals("viewProduct", viewName);
    }


}
