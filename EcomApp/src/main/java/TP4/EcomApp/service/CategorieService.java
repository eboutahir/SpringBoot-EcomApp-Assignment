package TP4.EcomApp.service;

import TP4.EcomApp.Repositories.CategorieRepository;
import TP4.EcomApp.entity.Categorie;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> getCategorieById(Long categorieId) {
        return categorieRepository.findById(categorieId);
    }

    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie updateCategorie(Long categorieId, Categorie updatedCategorie) {
       Categorie existingCategorie = categorieRepository.findById(categorieId).orElse(null);
        if (existingCategorie!=null) {

            existingCategorie.setName(updatedCategorie.getName());

            return categorieRepository.save(existingCategorie);
        } else {

            throw new EntityNotFoundException("Catégorie non trouvée avec l'ID : " + categorieId);
        }
    }

    public void deleteCategorie(Long categorieId) {
        Categorie existingCategorie = categorieRepository.findById(categorieId).orElse(null);
        if (existingCategorie!=null) {
            categorieRepository.deleteById(categorieId);
        } else {
            throw new EntityNotFoundException("Catégorie non trouvée avec l'ID : " + categorieId);
        }
    }
}
