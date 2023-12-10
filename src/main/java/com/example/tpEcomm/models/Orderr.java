package com.example.tpEcomm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderr {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userinfo;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItem;
    private Float totalPrice;
    private Date orderDate;
    @Column(name = "is_deleted")
    private boolean deleted;

    public Orderr(User userinfo, List<OrderItem> orderItem, Float totalPrice, Date orderDate, boolean deleted) {
        this.userinfo = userinfo;
        this.orderItem = orderItem;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.deleted = deleted;
    }
}
