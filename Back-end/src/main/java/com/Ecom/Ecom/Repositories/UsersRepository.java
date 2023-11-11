package com.Ecom.Ecom.Repositories;

import com.Ecom.Ecom.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users getById(Long  idTache);
    Users findByUsername(String username);
}
