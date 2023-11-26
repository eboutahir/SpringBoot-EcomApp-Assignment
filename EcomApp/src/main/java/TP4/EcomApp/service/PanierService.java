package TP4.EcomApp.service;

import TP4.EcomApp.Repositories.PanierRepository;
import TP4.EcomApp.entity.Panier;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierService {
    private final PanierRepository panierRepository;

    @Autowired
    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    public List<Panier> getAllPaniers() {
        return panierRepository.findAll();
    }

    public Panier getPanierById(Long panierId) {
        return panierRepository.findById(panierId).orElse(null);
    }

    public Panier createPanier(Panier panier) {
        return panierRepository.save(panier);
    }

    public Panier updatePanier(Long panierId, Panier updatedPanier) {
        Panier existingPanier = panierRepository.findById(panierId).orElse(null);
        if (existingPanier!=null) {

            existingPanier.setUser(updatedPanier.getUser());

            return panierRepository.save(existingPanier);
        } else {

            throw new EntityNotFoundException("Panier non trouvé avec l'ID : " + panierId);
        }
    }

    public void deletePanier(Long panierId) {
        Panier existingPanier = panierRepository.findById(panierId).orElse(null);
        if (existingPanier!=null)  {
            panierRepository.deleteById(panierId);
        } else {
            throw new EntityNotFoundException("Panier non trouvé avec l'ID : " + panierId);
        }
    }
}

