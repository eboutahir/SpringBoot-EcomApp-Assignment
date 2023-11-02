package com.example.Application.ECommerce.services;

import com.example.Application.ECommerce.models.User;

import java.util.List;

public interface UserService {
    User getByID(Long id);
    User findByEmail(String Email);
    List<User> findAll();
    User findByUsername(String username);
    User save(User user);
    boolean addUser(User user);
}
