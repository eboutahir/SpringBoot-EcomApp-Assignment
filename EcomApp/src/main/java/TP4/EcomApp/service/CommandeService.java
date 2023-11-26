package TP4.EcomApp.service;

import TP4.EcomApp.Repositories.CommandeRepository;
import TP4.EcomApp.entity.Commande;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Long commandeId) {
        return commandeRepository.findById(commandeId).orElse(null);
    }

    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(Long commandeId, Commande updatedCommande) {
        Commande existingCommande = commandeRepository.findById(commandeId).orElse(null);
        if (existingCommande!=null) {

            existingCommande.setDateCommande(updatedCommande.getDateCommande());
            existingCommande.setStatutCommande(updatedCommande.getStatutCommande());
            existingCommande.setUser(updatedCommande.getUser());
            existingCommande.setPanier(updatedCommande.getPanier());


            return commandeRepository.save(existingCommande);
        } else {

            throw new EntityNotFoundException("Commande non trouvé avec l'ID : " + commandeId);
        }
    }

    public void deleteCommande(Long commandeId) {
        Commande existingCommande = commandeRepository.findById(commandeId).orElse(null);
        if (existingCommande!=null) {
            commandeRepository.deleteById(commandeId);
        } else {
            throw new EntityNotFoundException("Commande non trouvé avec l'ID : " + commandeId);
        }
    }
}

