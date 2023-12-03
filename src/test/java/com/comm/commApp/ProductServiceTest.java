package com.comm.commApp;

import com.comm.commApp.Model.Product;

import com.comm.commApp.Service.ProductService;
import com.comm.commApp.repository.PruductRepository;
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
class ProductServiceTest {

    @Mock
    private PruductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getAllProductTest() {

        Product product1 = new Product();
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setName("Product 2");

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);


        List<Product> result = productService.getAllProduct();


        assertEquals(2, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));
    }

    @Test
    void addProductTest() {

        Product product = new Product();
        product.setName("New Product");


        productService.addProduct(product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void delProductTest() {

        long productId = 1;


        productService.delProduct(productId);


        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void getProductTest() {

        long productId = 1;
        Product product = new Product();
        product.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));


        Optional<Product> result = productService.getProduct(productId);


        assertTrue(result.isPresent());
        assertEquals(productId, result.get().getId());
    }

    @Test
    void getProductNotFoundTest() {

        long productId = 1;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());


        Optional<Product> result = productService.getProduct(productId);


        assertFalse(result.isPresent());
    }


}
