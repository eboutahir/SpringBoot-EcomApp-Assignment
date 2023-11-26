package TP4.EcomApp.service;

import static org.junit.jupiter.api.Assertions.*;


import TP4.EcomApp.Repositories.CategorieRepository;
import TP4.EcomApp.entity.Categorie;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CategorieServiceTest {

    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieService categorieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllCategories() {

        List<Categorie> categories = new ArrayList<>();
        when(categorieRepository.findAll()).thenReturn(categories);

        // Test
        List<Categorie> result = categorieService.getAllCategories();

        // Verify
        assertEquals(categories, result);
    }

    @Test
    void getCategorieById() {

        Long categorieId = 1L;
        Categorie categorie = new Categorie();
        when(categorieRepository.findById(categorieId)).thenReturn(Optional.of(categorie));

        // Test
        Optional<Categorie> result = categorieService.getCategorieById(categorieId);

        // Verify
        assertTrue(result.isPresent());
        assertEquals(categorie, result.get());
    }

    @Test
    void createCategorie() {

        Categorie categorie = new Categorie();
        when(categorieRepository.save(categorie)).thenReturn(categorie);

        // Test
        Categorie result = categorieService.createCategorie(categorie);

        // Verify
        assertEquals(categorie, result);
    }

    @Test
    void updateCategorie() {

        Long categorieId = 1L;
        Categorie existingCategorie = new Categorie();
        existingCategorie.setId(categorieId);
        when(categorieRepository.findById(categorieId)).thenReturn(Optional.of(existingCategorie));

        Categorie updatedCategorie = new Categorie();
        updatedCategorie.setName("UpdatedName");

        // Test
        Categorie result = categorieService.updateCategorie(categorieId, updatedCategorie);

        // Verify
        assertEquals(updatedCategorie.getName(), result.getName());
    }

    @Test
    void updateCategorieNotFound() {

        Long categorieId = 1L;
        when(categorieRepository.findById(categorieId)).thenReturn(Optional.empty());

        Categorie updatedCategorie = new Categorie();

        // Test and Verify
        assertThrows(EntityNotFoundException.class,
                () -> categorieService.updateCategorie(categorieId, updatedCategorie),
                "Catégorie non trouvée avec l'ID : " + categorieId);
    }

    @Test
    void deleteCategorie() {

        Long categorieId = 1L;
        Categorie existingCategorie = new Categorie();
        when(categorieRepository.findById(categorieId)).thenReturn(Optional.of(existingCategorie));

        // Test
        categorieService.deleteCategorie(categorieId);

        // Verify
        verify(categorieRepository, times(1)).deleteById(categorieId);
    }

    @Test
    void deleteCategorieNotFound() {

        Long categorieId = 1L;
        when(categorieRepository.findById(categorieId)).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class,
                () -> categorieService.deleteCategorie(categorieId),
                "Catégorie non trouvée avec l'ID : " + categorieId);
    }
}
