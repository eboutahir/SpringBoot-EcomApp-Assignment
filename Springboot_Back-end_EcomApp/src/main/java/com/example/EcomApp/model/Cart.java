package com.example.EcomApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="Cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Long Quantity;
    @ManyToOne
    @JoinColumn(name="idProduct" , nullable=true,referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name="idUser" , nullable=true,referencedColumnName = "id")
    private User user;


}