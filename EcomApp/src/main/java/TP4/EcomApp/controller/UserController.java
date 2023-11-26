package TP4.EcomApp.controller;

import TP4.EcomApp.entity.User;
import TP4.EcomApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/Tp4/users")

public class UserController
  {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userInfo) {
        User createdUser = userService.createUser(userInfo);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

      @CrossOrigin(origins = "http://localhost:4200")
      @GetMapping
      @PreAuthorize("hasAuthority('ROLE_ADMIN')")
      public ResponseEntity<List<User>> getAllUsers(@RequestHeader HttpHeaders headers) {
          System.out.println("Request Headers: " + headers);
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
          System.out.println("Authorities: " + authorities); // Vérifiez les autorités dans les logs ou la console

          List<User> users = userService.getAllUsers();
          return new ResponseEntity<>(users, HttpStatus.OK);
      }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

