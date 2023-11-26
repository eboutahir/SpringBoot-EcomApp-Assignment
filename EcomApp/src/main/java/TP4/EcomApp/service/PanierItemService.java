package TP4.EcomApp.service;

import TP4.EcomApp.Repositories.PanierItemRepository;
import TP4.EcomApp.entity.Panier;
import TP4.EcomApp.entity.PanierItem;
import TP4.EcomApp.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierItemService
{

    @Autowired
    private PanierItemRepository panierItemRepository;

    public PanierItem getPanierItemById(Long panierItemId) {
        PanierItem panierItemOptional = panierItemRepository.findById(panierItemId).orElse(null);
        return panierItemOptional;
    }
    public PanierItem ajouterProduitAuPanier(Produit produit, Panier panier, int quantite)
    {
        PanierItem panierItem = new PanierItem();
        panierItem.setProduit(produit);
        panierItem.setPanier(panier);
        panierItem.setQuantity(quantite);
        return panierItemRepository.save(panierItem);
    }

    public List<PanierItem> getPanierItemsByPanier(Panier panier)
    {
        return panierItemRepository.findByPanier(panier);
    }

    public void DeletePanierItem(PanierItem panierItem)
    {
        panierItemRepository.delete(panierItem);
    }
}

