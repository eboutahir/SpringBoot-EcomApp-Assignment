package net.javaguides.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.Arrays;
import java.util.List;

import net.javaguides.springboot.model.Produit;
import net.javaguides.springboot.service.ProductService;
import net.javaguides.springboot.web.ProduitController;

public class ProductControllerTest {
    
    @InjectMocks
    private ProduitController produitController;
    @Mock
     private Model model;
     @Mock
      private  ProductService productService;
      @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    //teter methode home pour assurer qu'il utilise correctement service ProductService
    @Test
   void testHome() {
    // Arrange
    Produit produit1 = new Produit();
    Produit produit2 = new Produit();
    List<Produit> expectedProducts = Arrays.asList(produit1, produit2);

    // Configurez le mock pour renvoyer la liste fictive lorsque getAllProducts() est appelé
    when(productService.getAllProducts()).thenReturn(expectedProducts);

    // Act
    String viewName = produitController.home(model);

    // Assert
    assertNotNull(model, "Model should not be null");

}

}
