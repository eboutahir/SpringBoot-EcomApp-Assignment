package com.Ecom.Ecom.Services;


import com.Ecom.Ecom.Models.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {

    List<Users> getAllUsers();
    Users createUser(Users user);
    String hashPassword(String password);
}
