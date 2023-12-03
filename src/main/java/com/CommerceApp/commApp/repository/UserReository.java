package com.CommerceApp.commApp.repository;

import com.CommerceApp.commApp.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



public interface UserReository extends JpaRepository<User,Integer> {
    Optional<User> findUserByEmail(String email);
}
