package TP4.EcomApp.controller;

import TP4.EcomApp.entity.Panier;
import TP4.EcomApp.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tp4/paniers")
public class PanierController {
    private final PanierService panierService;

    @Autowired
    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Panier> getAllPaniers() {
        return panierService.getAllPaniers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Panier getPanierById(@PathVariable Long id) {
        return panierService.getPanierById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Panier createPanier(@RequestBody Panier panier) {
        return panierService.createPanier(panier);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Panier updatePanier(@PathVariable Long id, @RequestBody Panier updatedPanier) {
        return panierService.updatePanier(id, updatedPanier);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deletePanier(@PathVariable Long id) {
        panierService.deletePanier(id);
    }
}

