package com.example.demo.RegisterModel;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class produit {
 @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
@NonNull
private String name;
@NonNull
private long prix;
@NonNull
private String image;
@NonNull
private String desciption;

}
