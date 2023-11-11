package com.Ecom.Ecom.Services.ServicesImp;


import com.Ecom.Ecom.Models.Users;
import com.Ecom.Ecom.Repositories.UsersRepository;
import com.Ecom.Ecom.Services.UsersService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UsersServiceImp implements UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users createUser(Users user) {
        // Vous pouvez ajouter des validations ici, par exemple, vérifier si le nom d'utilisateur est unique.
        return userRepository.save(user);
    }
    public String hashPassword(String password) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Get the password bytes
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

            // Update the digest with the password bytes
            byte[] hashedBytes = digest.digest(passwordBytes);

            // Convert the hashed bytes to a hexadecimal string
            StringBuilder hexString = new StringBuilder(2 * hashedBytes.length);
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception, e.g., log it or throw a custom exception
            e.printStackTrace();
            return null;
        }
    }

}
