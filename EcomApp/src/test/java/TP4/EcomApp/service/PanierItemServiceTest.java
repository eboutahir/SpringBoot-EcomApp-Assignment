package TP4.EcomApp.service;

import TP4.EcomApp.Repositories.PanierItemRepository;
import TP4.EcomApp.entity.Panier;
import TP4.EcomApp.entity.PanierItem;
import TP4.EcomApp.entity.Produit;
import TP4.EcomApp.service.PanierItemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PanierItemServiceTest {

    @Mock
    private PanierItemRepository panierItemRepository;

    @InjectMocks
    private PanierItemService panierItemService;

    public PanierItemServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPanierItemById() {
        // Arrange
        Long panierItemId = 1L;
        PanierItem panierItem = new PanierItem();
        panierItem.setId(panierItemId);
        when(panierItemRepository.findById(panierItemId)).thenReturn(Optional.of(panierItem));

        // Act
        PanierItem result = panierItemService.getPanierItemById(panierItemId);

        // Assert
        assertNotNull(result);
        assertEquals(panierItemId, result.getId());
    }

    @Test
    void testAjouterProduitAuPanier() {

        Produit produit = new Produit();
        Panier panier = new Panier();
        int quantite = 2;


        when(panierItemRepository.save(any(PanierItem.class))).thenReturn(new PanierItem());


        PanierItem result = panierItemService.ajouterProduitAuPanier(produit, panier, quantite);


        assertNotNull(result, "The result should not be null");
        assertNotNull(result.getProduit(), "The product should not be null");
        assertEquals(produit, result.getProduit(), "The product should match");
        assertEquals(panier, result.getPanier(), "The panier should match");
        assertEquals(quantite, result.getQuantity(), "The quantity should match");


        verify(panierItemRepository, times(1)).save(any(PanierItem.class));
    }


    @Test
    void testGetPanierItemsByPanier() {

        Panier panier = new Panier();
        PanierItem panierItem = new PanierItem();
        panierItem.setPanier(panier);
        when(panierItemRepository.findByPanier(panier)).thenReturn(Collections.singletonList(panierItem));

        // Act
        List<PanierItem> result = panierItemService.getPanierItemsByPanier(panier);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(panier, result.get(0).getPanier());
    }

    @Test
    void testDeletePanierItem() {

        PanierItem panierItem = new PanierItem();


        panierItemService.DeletePanierItem(panierItem);


        verify(panierItemRepository, times(1)).delete(panierItem);
    }
}
