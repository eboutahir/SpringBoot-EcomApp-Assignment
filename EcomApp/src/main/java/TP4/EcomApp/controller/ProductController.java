package TP4.EcomApp.controller;

import TP4.EcomApp.config.AuthRequest;
import TP4.EcomApp.entity.Product;
import TP4.EcomApp.entity.User;
import TP4.EcomApp.service.JwtService;
import TP4.EcomApp.service.ProductService;
import TP4.EcomApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody User userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }


    @PostMapping("/register")
    public String registerUser(@RequestBody User userInfo) {

        User createdUser = userService.createUser(userInfo);
        return "Utilisateur créé avec succès : " + createdUser.getName();
    }
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        User user = userService.getUserByName(authRequest.getUsername());

        if (user != null && authRequest.getPassword().equals(user.getPassword())) {

            String token = jwtService.generateToken(authRequest.getUsername());
            return token;
        } else {
            throw new UsernameNotFoundException("Authentification échouée !");
        }
    }


}