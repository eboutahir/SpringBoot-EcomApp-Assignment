package TP4.EcomApp.controller;

import TP4.EcomApp.entity.Panier;
import TP4.EcomApp.entity.PanierItem;
import TP4.EcomApp.entity.Produit;
import TP4.EcomApp.service.PanierItemService;
import TP4.EcomApp.service.PanierService;
import TP4.EcomApp.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tp4/panier-items")
public class PanierItemController {

    @Autowired
    private PanierItemService panierItemService;
    @Autowired
    private ProduitService produitService;
    @Autowired
    private PanierService panierService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public PanierItem ajouterProduitAuPanier(@RequestParam Long produitId,@RequestParam Long panierId, @RequestParam int quantite) {
        Produit produit = produitService.getProduitById(produitId);
        Panier panier = panierService.getPanierById(panierId);

        if (produit != null && panier != null) {
            return panierItemService.ajouterProduitAuPanier(produit, panier, quantite);
        } else {
            return null;
        }
    }

    @GetMapping("/panier/{panierId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<PanierItem> getPanierItemsByPanier(@PathVariable Long panierId) {
        Panier panier = panierService.getPanierById(panierId);
        if (panier != null) {
            return panierItemService.getPanierItemsByPanier(panier);
        } else {

            return null;
        }
    }

    @DeleteMapping("/{panierItemId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void supprimerPanierItem(@PathVariable Long panierItemId) {
        PanierItem panierItem = panierItemService.getPanierItemById(panierItemId);
        if (panierItem != null) {
            panierItemService.DeletePanierItem(panierItem);
        } else {

        }
    }
}

