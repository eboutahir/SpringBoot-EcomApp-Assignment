package TP4.EcomApp.service;

import static org.junit.jupiter.api.Assertions.*;


import TP4.EcomApp.Repositories.CommandeRepository;
import TP4.EcomApp.entity.Commande;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CommandeServiceTest {

    @Mock
    private CommandeRepository commandeRepository;

    @InjectMocks
    private CommandeService commandeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllCommandes() {

        List<Commande> commandes = new ArrayList<>();
        when(commandeRepository.findAll()).thenReturn(commandes);

        // Test
        List<Commande> result = commandeService.getAllCommandes();

        // Verify
        assertEquals(commandes, result);
    }

    @Test
    void getCommandeById() {

        Long commandeId = 1L;
        Commande commande = new Commande();
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(commande));

        // Test
        Commande result = commandeService.getCommandeById(commandeId);

        // Verify
        assertNotNull(result);
        assertEquals(commande, result);
    }

    @Test
    void createCommande() {

        Commande commande = new Commande();
        when(commandeRepository.save(commande)).thenReturn(commande);

        // Test
        Commande result = commandeService.createCommande(commande);

        // Verify
        assertEquals(commande, result);
    }

    @Test
    void updateCommande() {

        Long commandeId = 1L;
        Commande existingCommande = new Commande();
        existingCommande.setId(commandeId);
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(existingCommande));

        Commande updatedCommande = new Commande();
        updatedCommande.setDateCommande(new Date());
        updatedCommande.setStatutCommande("UpdatedStatut");


        Commande result = commandeService.updateCommande(commandeId, updatedCommande);


        assertNotNull(result, "La commande mise à jour ne doit pas être null");


        assertEquals(updatedCommande.getDateCommande(), result.getDateCommande());
        assertEquals(updatedCommande.getStatutCommande(), result.getStatutCommande());

    }




    @Test
    void updateCommandeNotFound() {

        Long commandeId = 1L;
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.empty());

        Commande updatedCommande = new Commande();

        // Test and Verify
        assertThrows(EntityNotFoundException.class,
                () -> commandeService.updateCommande(commandeId, updatedCommande),
                "Commande non trouvée avec l'ID : " + commandeId);
    }

    @Test
    void deleteCommande() {

        Long commandeId = 1L;
        Commande existingCommande = new Commande();
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(existingCommande));

        // Test
        commandeService.deleteCommande(commandeId);

        // Verify
        verify(commandeRepository, times(1)).deleteById(commandeId);
    }

    @Test
    void deleteCommandeNotFound() {

        Long commandeId = 1L;
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.empty());

        // Test and Verify
        assertThrows(EntityNotFoundException.class,
                () -> commandeService.deleteCommande(commandeId),
                "Commande non trouvée avec l'ID : " + commandeId);
    }
}
