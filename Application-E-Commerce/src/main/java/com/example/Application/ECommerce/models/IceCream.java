package com.example.Application.ECommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "description", "icecreamTitle", "icecreamSaveur", "price"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class IceCream {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Fill in the input field")
    private Integer price;
    @NotBlank(message = "Fill in the input field")
    private String icecreamTitle;
    @NotBlank(message = "Fill in the input field")

    private String poids;
    @NotBlank(message = "Fill in the input field")
    private String description;
    @NotBlank(message = "Fill in the input field")
    private String icecreamSaveur;
    @NotBlank(message = "Fill in the input field")
    private String image;

}
