package TP4.EcomApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @NonNull
    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private int stockQuantity;

    //@Getter
    //@Setter
   // private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    @Getter
    @Setter
    private Categorie categorie;

    public Produit() {
    }

    public Produit(String name, String description, @NonNull double price, int stockQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}

