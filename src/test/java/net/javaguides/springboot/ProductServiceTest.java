package net.javaguides.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import net.javaguides.springboot.model.Produit;
import net.javaguides.springboot.repository.ProduitReposytry;
import net.javaguides.springboot.service.ProductService;
import java.util.Arrays;
import java.util.List;
public class ProductServiceTest {
   @InjectMocks
    private ProductService productService;
    @Mock
     private ProduitReposytry produitReposytry;
     
     
     @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testGetAllProducts() {
        // Arrange
        Produit produit1 = new Produit();
        Produit produit2 = new Produit();
        List<Produit> expectedProducts = Arrays.asList(produit1, produit2);

        // Configurez le mock pour renvoyer la liste fictive lorsque findAll() est appelé
        when(produitReposytry.findAll()).thenReturn(expectedProducts);

        // Act
        List<Produit> actualProducts = productService.getAllProducts();

        // Assert
        assertEquals(expectedProducts.size(), actualProducts.size());
        assertEquals(expectedProducts.get(0), actualProducts.get(0));
        assertEquals(expectedProducts.get(1), actualProducts.get(1));

       assertFalse(actualProducts.isEmpty(), "La liste ne devrait pas être vide");



    }
}







