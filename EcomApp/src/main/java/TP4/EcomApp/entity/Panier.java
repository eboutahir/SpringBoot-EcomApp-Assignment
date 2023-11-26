package TP4.EcomApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    @JsonIgnore
    @Getter
    @Setter
    private List<PanierItem> panierItems;


    @JsonIgnore
    @Getter
    @Setter
    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private List<Commande> commandes;

}

