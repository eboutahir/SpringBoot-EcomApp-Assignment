package com.example.tpEcomm.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orderr order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    public void setOrder(Orderr order) {
        this.order = order;
    }


}
