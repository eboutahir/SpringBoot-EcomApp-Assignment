package com.example.APPEcom.Comptes.Repositories;

import com.example.APPEcom.Comptes.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<User ,Long> {
    User findByEmail(String email);
}
