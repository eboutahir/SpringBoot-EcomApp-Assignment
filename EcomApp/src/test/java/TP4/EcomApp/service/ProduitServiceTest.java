package TP4.EcomApp.service;

import TP4.EcomApp.Repositories.ProduitRepository;
import TP4.EcomApp.entity.Produit;
import ch.qos.logback.core.spi.ErrorCodes;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProduitServiceTest {

    @MockBean
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitService produitService;

    @Test
    @DirtiesContext
    void itShouldGetAllProducts() {
        // Given
        Produit produit1 = new Produit("Produit1", "Description1", 20.0, 100);
        Produit produit2 = new Produit("Produit2", "Description2", 30.0, 50);

        when(produitRepository.findAll()).thenReturn(List.of(produit1, produit2));

        // When
        List<Produit> produits = produitService.getAllProducts();

        // Then
        assertThat(produits).isNotNull();
        assertThat(produits).hasSize(2);
        assertThat(produits).contains(produit1, produit2);
    }

    @Test
    @DirtiesContext
    void itShouldGetProductById() {
        // Given
        Produit produit = new Produit("Produit1", "Description1", 20.0, 100);
        when(produitRepository.findById(produit.getId())).thenReturn(Optional.of(produit));


        // When
        Produit retrievedProduit = produitService.getProduitById(produit.getId());

        // Then
        assertThat(retrievedProduit).isNotNull();
        assertThat(retrievedProduit).isEqualTo(produit);
    }

    @Test
    @DirtiesContext
    void itShouldCreateProduct() {
        // Given
        Produit newProduit = new Produit("NewProduit", "NewDescription", 25.0, 75);
        when(produitRepository.save(any(Produit.class))).thenAnswer(invocation -> {
            Produit savedProduit = invocation.getArgument(0);
            savedProduit.setId(1L); // simulate setting the ID during save
            return savedProduit;
        });

        // When
        Produit createdProduit = produitService.createProduit(newProduit);

        System.out.println("Expected Product: " + newProduit);
        System.out.println("Created Product: " + createdProduit);
        System.out.println("Objects are equal: " + newProduit.equals(createdProduit));


        // Then
        // Assurer que l'objet créé n'est pas null avant l'assertion
        assertNotNull(createdProduit, "Created product should not be null");
        assertThat(createdProduit).isNotNull();
        assertThat(createdProduit.getId()).isNotNull();
        assertThat(createdProduit.getName()).isEqualTo(newProduit.getName());
        assertThat(createdProduit.getDescription()).isEqualTo(newProduit.getDescription());
        assertThat(createdProduit.getPrice()).isEqualTo(newProduit.getPrice());
        assertThat(createdProduit.getStockQuantity()).isEqualTo(newProduit.getStockQuantity());
    }

    @Test
    void itShouldUpdateProduct() {
        // Given
        Produit existingProduit = new Produit("ExistingProduit", "ExistingDescription", 30.0, 50);
        when(produitRepository.findById(existingProduit.getId())).thenReturn(Optional.of(existingProduit));

        // Mock the save method to set the ID during save
        when(produitRepository.save(any(Produit.class))).thenAnswer(invocation -> {
            Produit savedProduit = invocation.getArgument(0);
            savedProduit.setId(existingProduit.getId()); // simulate setting the ID during save
            return savedProduit;
        });

        Long existingProduitId = existingProduit.getId();
        Produit updatedProduit = new Produit("UpdatedProduit", "UpdatedDescription", 30.0, 100);

        System.out.println("Expected Product ID: " + existingProduitId);

        // When
        Produit result = produitService.updateProduct(existingProduitId, updatedProduit);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(existingProduitId);
        assertThat(result.getName()).isEqualTo(updatedProduit.getName());
        assertThat(result.getDescription()).isEqualTo(updatedProduit.getDescription());
        assertThat(result.getPrice()).isEqualTo(updatedProduit.getPrice());
        assertThat(result.getStockQuantity()).isEqualTo(updatedProduit.getStockQuantity());
    }


    @Test
    void itShouldDeleteProduct() {
        // Given
        Produit existingProduit = new Produit("ExistingProduit", "ExistingDescription", 30.0, 50);
        produitRepository.save(existingProduit);

        // When
        produitService.deleteProduit(existingProduit.getId());

        // Then
        assertThat(produitRepository.findById(existingProduit.getId())).isEmpty();
    }








}