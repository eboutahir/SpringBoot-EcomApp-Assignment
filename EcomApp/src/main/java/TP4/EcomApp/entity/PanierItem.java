package TP4.EcomApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class PanierItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    @Getter
    @Setter
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "panier_id")
    @Getter
    @Setter
    private Panier panier;


}

