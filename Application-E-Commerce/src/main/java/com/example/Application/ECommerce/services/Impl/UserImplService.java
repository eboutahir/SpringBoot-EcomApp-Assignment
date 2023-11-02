package com.example.Application.ECommerce.services.Impl;

import com.example.Application.ECommerce.models.User;
import com.example.Application.ECommerce.respositories.UserRepository;
import com.example.Application.ECommerce.services.UserService;

import java.util.List;

public class UserImplService implements UserService {
   public UserRepository userRepository;
    @Override
    public User getByID(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }


}
