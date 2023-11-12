package com.example.EcomApp.repositories;


import com.example.EcomApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositories extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

}
