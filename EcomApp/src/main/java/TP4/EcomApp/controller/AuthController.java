package TP4.EcomApp.controller;

import TP4.EcomApp.config.AuthRequest;
import TP4.EcomApp.entity.User;
import TP4.EcomApp.service.JwtService;
import TP4.EcomApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/Tp4")
public class AuthController
{

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public String registerUser(@RequestBody User userInfo) {

        User createdUser = userService.createUser(userInfo);
        return "Utilisateur créé avec succès : " + createdUser.getName();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateAndGetToken(
            @RequestBody AuthRequest authRequest,
            @RequestHeader HttpHeaders headers
    ) {
        User user = userService.getUserByName(authRequest.getUsername());

        if (user != null && authRequest.getPassword().equals(user.getPassword())) {
            String token = jwtService.generateToken(authRequest.getUsername());
            List<String> roles = user.getRoles().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("roles", roles);
           headers.remove("Authorization");
            System.out.println("Response Headers: " + headers);
           // headers.add("Authorization", "Bearer " + token);
            System.out.println("Response Headers: " + headers);
            System.out.println("Token: " + token);
            System.out.println("Roles: " + roles);

            return ResponseEntity.ok()

                    .body(response);
        } else {
            System.out.println("Authentication failed for username: " + authRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Authentication failed!"));
        }
    }


}
