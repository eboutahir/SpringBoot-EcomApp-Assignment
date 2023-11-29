package com.example.EcomApp.service;

import com.example.EcomApp.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.EcomApp.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    public void testGetAllProducts() {


        Product product1 = new Product(1L, "Product 1", "image1.jpg", "Description 1", "Category A", 19.99);
        Product product2 = new Product(2L, "Product 2", "image2.jpg", "Description 2", "Category B", 29.99);
        List<Product> mockProducts = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> actualProducts = productService.getAllProducts();

        assertEquals(2, actualProducts.size());
        assertEquals("Product 1", actualProducts.get(0).getTitle());
        assertEquals("Category B", actualProducts.get(1).getCategory());
    }

    @Test
    public void testGetProductsByCategory() {

        String categoryToSearch = "Category A";
        Product product1 = new Product(1L, "Product 1", "image1.jpg", "Description 1", "Category A", 194);
        Product product2 = new Product(2L, "Product 2", "image2.jpg", "Description 2", "Category A", 234);
        List<Product> mockProducts = Arrays.asList(product1, product2);

        when(productRepository.findByCategory(categoryToSearch)).thenReturn(mockProducts);

        List<Product> productsByCategory = productService.getProductsByCategory(categoryToSearch);

        assertEquals(2, productsByCategory.size());
        assertEquals("Product 1", productsByCategory.get(0).getTitle());
        assertEquals("Description 2", productsByCategory.get(1).getDescription());

    }

    public void testGetProductById() {
        Long productId = 1L;
        Product mockProduct = new Product(productId, "Product", "image.jpg", "description", "Category", 25);

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        Optional<Product> productById = productService.getById(productId);

        assertTrue(productById.isPresent());
        assertEquals("Test Product", productById.get().getTitle());
        assertEquals("Test Category", productById.get().getCategory());
    }

    @Test
    public void testCreateProduct() {
        Product newProduct = new Product(null, "Product", "image.jpg", "Description", "Category", 49);

        when(productRepository.save(any(Product.class))).thenReturn(newProduct);

        Product savedProduct = productService.createProduct(newProduct);

        assertNotNull(savedProduct);
        assertEquals("New Product", savedProduct.getTitle());
        assertEquals(49, savedProduct.getPrice(), 0);
    }


    @Test
    public void testUpdateProduct() {

        Long productId = 1L;
        Product existingProduct = new Product(productId, "Existing Product", "existing_image.jpg", "Existing Description", "Existing Category", 39.99);
        Product updatedProduct = new Product(productId, "Updated Product", "updated_image.jpg", "Updated Description", "Updated Category", 59.99);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProduct(productId, updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Product", result.getTitle());
        assertEquals("Updated Category", result.getCategory());
        assertEquals(59.99, result.getPrice(), 0.01);
    }


}