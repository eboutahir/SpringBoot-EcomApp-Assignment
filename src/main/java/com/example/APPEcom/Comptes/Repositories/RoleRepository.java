package com.example.APPEcom.Comptes.Repositories;

import com.example.APPEcom.Comptes.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
