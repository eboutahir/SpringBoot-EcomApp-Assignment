package com.Zazahome.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order {

    @Id
    private Long id;

    // Other fields and methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

