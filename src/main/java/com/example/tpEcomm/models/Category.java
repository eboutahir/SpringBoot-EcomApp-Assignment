package com.example.tpEcomm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;
    private String name;
    private boolean is_deleted;

    public Category(String name) {
        name=name;
        is_deleted=false;
    }
}
