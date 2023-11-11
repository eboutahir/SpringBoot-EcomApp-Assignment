package com.Ecom.Ecom.Models;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String Email;
    private String Tel;
}
