package com.example.EcomApp.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
@Data
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name", nullable = false)
        private String firstName;

        @Column(name = "last_name", nullable = false)
        private String lastName;

        @Column(name = "login", nullable = false)
        private String login;

        @Column(name = "password", nullable = false)
        private String password;

}
