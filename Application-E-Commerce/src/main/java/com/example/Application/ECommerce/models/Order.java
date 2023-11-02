package com.example.Application.ECommerce.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "user", "iceCreamsList"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;

    private LocalDate date;
    @NotBlank(message = "Fill in the input field")
    private String firstName;

    /**
     * The last name of the customer who placed the order.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotBlank(message = "Fill in the input field")
    private String lastName;

    /**
     * City of delivery of the order.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotBlank(message = "Fill in the input field")
    private String city;

    /**
     * Delivery address of the order.
     * The @NotBlank annotation says the field should not be empty.
     */
    @NotBlank(message = "Fill in the input field")
    private String address;

    @Email(message = "Incorrect email")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;

    @OrderColumn
    @ManyToMany(fetch = FetchType.EAGER)
    private List<IceCream> iceCreamsList;


    private User user;

    public Order(User user) {
        this.date = LocalDate.now();
        this.user = user;
        this.iceCreamsList= new ArrayList<>();
    }

}
