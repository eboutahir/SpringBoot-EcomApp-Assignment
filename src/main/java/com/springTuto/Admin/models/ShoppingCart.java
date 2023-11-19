package com.springTuto.Admin.models;

import com.springTuto.account.models.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
@Getter
@Setter
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String sessionToken;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    private Set<CartItem> items;
    @Transient
    private Double totalPrice;
    @Transient
    private int nbItems;
    @OneToOne(fetch = FetchType.EAGER,orphanRemoval = true)
    private User user;
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
    public ShoppingCart( ) {
       this.items = new HashSet<>();
    }
    public ShoppingCart(String sessionToken, Set<CartItem> items, Double totalPrice) {
        this.sessionToken = sessionToken;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", sessionToken='" + sessionToken + '\'' +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                ", nbItems=" + nbItems +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}