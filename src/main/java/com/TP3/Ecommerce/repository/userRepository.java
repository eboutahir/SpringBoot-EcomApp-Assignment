package com.TP3.Ecommerce.repository;

import com.TP3.Ecommerce.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user,Long> {
    user findByUsername(String username);
}
