package com.Ecomerce;
import Ecomerce.springTuto.Admin.dto.ProductDto;
import Ecomerce.springTuto.Admin.models.Category;
import Ecomerce.springTuto.Admin.models.Product;
import Ecomerce.springTuto.Admin.repositories.ProductRepository;
import Ecomerce.springTuto.Admin.servicies.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductDto productDto;
    private Product product;

    @Before
    public void setUp() {
        Category category = new Category();  // Instantiate a Category object
        category.setId(1L);
        category.setName("Test Category");

        productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setDescription("Test Description");
        productDto.setPrice(20.0);
        productDto.setStock(50);
        productDto.setReduction(5.0);
        MockMultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "Test Image Content".getBytes());
        productDto.setImageFile(imageFile);
        productDto.setCategory(category);  // Set the category in ProductDto

        product = new Product(productDto.getName(), productDto.getDescription(),
                productDto.getPrice(), productDto.getStock(), productDto.getReduction(),
                "Test Image Content", productDto.getCategory());
        product.setId(1L);
    }

    @Test
    public void testAddProduct() throws IOException {
        when(productRepository.save(any())).thenReturn(product);

        Product result = productService.addProduct(productDto);

        assertEquals(product, result);
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateProduct() throws IOException {
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(productRepository.save(any())).thenReturn(product);

        Product result = productService.updateProduct(1L, productDto);

        assertEquals(product, result);
        verify(productRepository, times(1)).findById(any());
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getAllProducts();

        assertEquals(productList, result);
        verify(productRepository, times(1)).findAll();
    }
}
