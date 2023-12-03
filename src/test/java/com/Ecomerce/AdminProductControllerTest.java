package com.Ecomerce;

import Ecomerce.springTuto.Admin.controllers.AdminProductController;
import Ecomerce.springTuto.Admin.dto.ProductDto;
import Ecomerce.springTuto.Admin.models.Category;
import Ecomerce.springTuto.Admin.models.Product;
import Ecomerce.springTuto.Admin.servicies.CategoryService;
import Ecomerce.springTuto.Admin.servicies.ProductService;
import Ecomerce.springTuto.account.models.User;
import Ecomerce.springTuto.account.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AdminProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private UserService userService;

    @Mock
    private CategoryService categoryService;
    @Mock
    private Model model;
    @InjectMocks
    private AdminProductController adminProductController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIndexView() {
        // Mocking data
        Model model = mock(Model.class);
        Authentication auth = mock(Authentication.class);
        User user = new User();
        when(auth.getName()).thenReturn("test@example.com");
        when(userService.getAccountByEmail("test@example.com")).thenReturn(user);
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        when(categoryService.getAllCategories()).thenReturn(new ArrayList<>());

        // Testing the method
        String result = adminProductController.indexView(model, auth, "");

        // Assertions
        assertEquals("Admin/index", result);
        verify(model, times(1)).addAttribute(eq("products"), any());
        verify(model, times(1)).addAttribute(eq("user"), any());
        verify(model, times(1)).addAttribute(eq("productDto"), any());
        verify(model, times(1)).addAttribute(eq("categories"), any());
    }

    @Test
    public void testAddProductView() {
        List<Category> mockCategories = new ArrayList<>();
        when(categoryService.getAllCategories()).thenReturn(mockCategories);

        String viewName = adminProductController.addProductView(model);

        // Verify that the getAllCategories method is called
        verify(categoryService).getAllCategories();

        // Verify that the model attribute "productDto" is added
        verify(model).addAttribute("productDto", new ProductDto());

        // Verify that the model attribute "categories" is added with the correct value
        verify(model).addAttribute("categories", mockCategories);

        // Add your assertions here to check the returned view name
        assertEquals("Admin/addproduct", viewName);
    }


    @Test
    public void testAddProduct() {
        // Mocking data
        ProductDto productDto = new ProductDto();

        // Testing the method
        String result = adminProductController.addProduct(productDto);

        // Assertions
        assertEquals("redirect:/Admin/index", result);
        verify(productService, times(1)).addProduct(productDto);
    }

    @Test
    public void testDelete() {
        // Mocking data
        Long id = 1L;

        // Testing the method
        String result = adminProductController.delete(id);

        // Assertions
        assertEquals("redirect:/Admin/index", result);
        verify(productService, times(1)).deleteProduct(id);
    }

    @Test
    public void testEditView() {
        // Mocking data
        Long id = 1L;
        Model model = mock(Model.class);
        Product product = new Product();
        when(productService.getProductById(id)).thenReturn(product);

        // Testing the method
        String result = adminProductController.editView(id, model);

        // Assertions
        assertEquals("Admin/editProduct", result);
        verify(model, times(1)).addAttribute(eq("product"), any());
    }

    @Test
    public void testEditProduct() {
        // Mocking data
        ProductDto productDto = new ProductDto();

        // Testing the method
        String result = adminProductController.editProduct(productDto);

        // Assertions
        assertEquals("redirect:/Admin/index", result);
        verify(productService, times(1)).updateProduct(productDto.getId(), productDto);
    }
}
