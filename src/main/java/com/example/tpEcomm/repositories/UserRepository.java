package com.example.tpEcomm.repositories;

import com.example.tpEcomm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository <User,Long>{
    Optional<User> findByUsername(String username);
}
