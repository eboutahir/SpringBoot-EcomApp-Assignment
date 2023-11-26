package TP4.EcomApp.service;

import TP4.EcomApp.Repositories.ProduitRepository;
import TP4.EcomApp.entity.Produit;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService
{
    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> getAllProducts() {
        return produitRepository.findAll();
    }

    public Produit getProduitById(Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }


    public Produit updateProduct(Long produitId, Produit updatedProduit) {
        Produit existingProduit = produitRepository.findById(produitId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + produitId));

        if (existingProduit != null) {

            existingProduit.setName(updatedProduit.getName());
            existingProduit.setDescription(updatedProduit.getDescription());
            existingProduit.setPrice(updatedProduit.getPrice());
            existingProduit.setStockQuantity(updatedProduit.getStockQuantity());


            return produitRepository.save(existingProduit);
        } else {

            throw new EntityNotFoundException("Produit non trouvé avec l'ID : " + produitId);
        }
    }

    public void deleteProduit(Long produitId) {
         Produit existingProduit = produitRepository.findById(produitId).orElse(null);
        if (existingProduit!=null) {
            produitRepository.deleteById(produitId);
        } else {
            throw new EntityNotFoundException("Produit non trouvé avec l'ID : " + produitId);
        }
    }

}
