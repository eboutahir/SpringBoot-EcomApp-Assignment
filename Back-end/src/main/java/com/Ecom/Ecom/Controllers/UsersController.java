package com.Ecom.Ecom.Controllers;


import com.Ecom.Ecom.Models.Users;
import com.Ecom.Ecom.Repositories.UsersRepository;
import com.Ecom.Ecom.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/AllUsers")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody Users user) {
        String hashedPassword = usersService.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        usersService.createUser(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration réussite.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Users user) {
        Users existingUser = usersRepository.findByUsername(user.getUsername());

        // Vérifier si l'utilisateur existe et les informations d'identification sont correctes
        if (existingUser != null && existingUser.getPassword().equals(usersService.hashPassword(user.getPassword()))) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Connexion réussie.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Échec de la connexion.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}
