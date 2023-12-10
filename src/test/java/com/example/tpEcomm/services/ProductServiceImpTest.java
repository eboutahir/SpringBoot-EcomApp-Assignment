package com.example.tpEcomm.services;

import com.example.tpEcomm.models.Category;
import com.example.tpEcomm.models.Product;
import com.example.tpEcomm.repositories.CategoryRepository;
import com.example.tpEcomm.repositories.ProductRepository;
import com.example.tpEcomm.services.ProductServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImpTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImp productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.findAll();

        assertEquals(productList, result);
    }

    @Test
    void testSaveProduct() {
        Category category = new Category();
        category.setName("Electronics");

        Product productToSave = new Product();
        productToSave.setCategoryName("Electronics");

        when(categoryRepository.findByName("Electronics")).thenReturn(category);
        when(productRepository.save(productToSave)).thenReturn(productToSave);

        Product savedProduct = productService.save(productToSave);

        assertEquals(productToSave, savedProduct);
        assertEquals(category, savedProduct.getCategory());
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Laptop");

        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Laptop");

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        Product result = productService.update(productId, updatedProduct);

        assertEquals(updatedProduct.getName(), result.getName());
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        Optional<Product> result = productService.getById(productId);

        assertTrue(result.isPresent());
        assertEquals(expectedProduct, result.get());
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);

        when(productRepository.getReferenceById(productId)).thenReturn(existingProduct);
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        productService.deleteProduct(productId);

        assertTrue(existingProduct.is_deleted());
    }

    @Test
    void testEnableProduct() {
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.set_deleted(true);
        when(productRepository.getReferenceById(productId)).thenReturn(existingProduct);
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);
        productService.EnableProduct(productId);
        assertFalse(existingProduct.is_deleted());
    }
}
