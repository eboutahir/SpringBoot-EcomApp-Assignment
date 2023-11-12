package com.example.demo.RegisterReposetry;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.RegisterModel.Registermodel;

public interface RegisterReposetry extends JpaRepository<Registermodel, Long> {

public boolean existsByEmail(String email);

public Registermodel  findByEmail(String email);




    
}