package com.comm.commApp;

import com.comm.commApp.Model.Product;

import com.comm.commApp.repository.PruductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private PruductRepository pruductRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveProductTest() {

        Product product = new Product();
        product.setName("Test Product");


        Product savedProduct = pruductRepository.save(product);


        assertEquals(product.getName(), savedProduct.getName());
        assertTrue(savedProduct.getId() > 0);


        entityManager.flush();
        entityManager.clear();

        Optional<Product> retrievedProduct = pruductRepository.findById(savedProduct.getId());
        assertTrue(retrievedProduct.isPresent());
        assertEquals(savedProduct.getName(), retrievedProduct.get().getName());
    }

    @Test
    void findAllByCategoryIdTest() {

        int categoryId = 1;


        List<Product> products = pruductRepository.findAllByCategoryId(categoryId);


        assertNotNull(products);
        assertTrue(products.isEmpty());
    }



    @Test
    void updateProductTest() {

        Product product = new Product();
        product.setName("Original Product");
        pruductRepository.save(product);


        Product retrievedProduct = pruductRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
        retrievedProduct.setName("Updated Product");
        Product updatedProduct = pruductRepository.save(retrievedProduct);


        assertEquals("Updated Product", updatedProduct.getName());


        entityManager.flush();
        entityManager.clear();

        Optional<Product> foundProduct = pruductRepository.findById(updatedProduct.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals("Updated Product", foundProduct.get().getName());
    }

    @Test
    void deleteProductTest() {

        Product product = new Product();
        product.setName("Product to Delete");
        pruductRepository.save(product);


        pruductRepository.delete(product);


        assertFalse(pruductRepository.existsById(product.getId()));
    }


}
