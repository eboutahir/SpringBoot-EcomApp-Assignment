package com.example.APPEcom.Produits.Services;

import static org.mockito.Mockito.*;

import com.example.APPEcom.Produits.DTO.ProductDto;
import com.example.APPEcom.Produits.Models.Product;
import com.example.APPEcom.Produits.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

public class ProductServiceTest {

        @Test
        public void testEdProductsuccessful() throws IOException {
                ProductRepository productRepositoryMock = mock(ProductRepository.class);

                ProductDto productDto = new ProductDto();
                productDto.setName("Test add Product");
                productDto.setDescription(" add Description");
                productDto.setPrice(100.0);
                productDto.setStock(50);
                productDto.setReduction(10.0);
                MockMultipartFile imageFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test data".getBytes());
                productDto.setImageFile(imageFile);

                ProductService productService = new ProductService(productRepositoryMock);

                productService.addProduct(productDto);

                verify(productRepositoryMock, times(1)).save(any(Product.class));
        }
        @Test
        public void testUpdateProductSuccessful() {
                // Création d'un ID factice pour simuler un produit existant
                long existingProductId = 2;

                // Mock de ProductRepository
                ProductRepository productRepositoryUpdateMock = mock(ProductRepository.class);

                // Configuration de ProductDto pour la mise à jour
                ProductDto productDto = new ProductDto();
                productDto.setId(existingProductId); // Set ID of the existing product
                productDto.setName("Test update Product");
                productDto.setDescription("update Description");
                productDto.setPrice(300.0);
                productDto.setStock(30);
                productDto.setReduction(5.0);
                MockMultipartFile imageFile = new MockMultipartFile("update image", "test_update.jpg", "image/jpeg", "test update data".getBytes());
                productDto.setImageFile(imageFile);

                // Création du ProductService avec le mock de ProductRepository
                ProductService productService = new ProductService(productRepositoryUpdateMock);

                // Appel de la méthode de mise à jour du produit
                productService.updateProduct(existingProductId, productDto);

                // Vérification si la méthode save ou update est appelée avec le bon produit
                verify(productRepositoryUpdateMock, times(1)).save(any(Product.class)); // Ou update selon votre logique

                // Autres assertions ou vérifications si nécessaire
        }

}
