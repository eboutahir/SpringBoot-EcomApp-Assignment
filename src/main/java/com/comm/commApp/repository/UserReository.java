package com.comm.commApp.repository;



import com.comm.commApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface UserReository extends JpaRepository<User,Integer> {
    Optional<User> findUserByEmail(String email);
}
