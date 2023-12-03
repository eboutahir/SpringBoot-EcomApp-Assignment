package com.comm.commApp;

import com.comm.commApp.Model.User;

import com.comm.commApp.repository.UserReository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserReository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveUserTest() {

        User user = new User();
        user.setEmail("test@example.com");


        User savedUser = userRepository.save(user);


        assertEquals(user.getEmail(), savedUser.getEmail());
        assertTrue(savedUser.getId() > 0);


        entityManager.flush();
        entityManager.clear();

        Optional<User> retrievedUser = userRepository.findById(savedUser.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals(savedUser.getEmail(), retrievedUser.get().getEmail());
    }

    @Test
    void findUserByEmailTest() {

        User user = new User();
        user.setEmail("test@example.com");
        userRepository.save(user);


        Optional<User> foundUser = userRepository.findUserByEmail("test@example.com");


        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void findUserByEmailNotFoundTest() {

        Optional<User> foundUser = userRepository.findUserByEmail("nonexistent@example.com");


        assertFalse(foundUser.isPresent());
    }

    @Test
    void updateUserTest() {

        User user = new User();
        user.setEmail("original@example.com");
        userRepository.save(user);


        User retrievedUser = userRepository.findUserByEmail("original@example.com").orElseThrow(() -> new RuntimeException("email not found"));
        retrievedUser.setEmail("updated@example.com");
        User updatedUser = userRepository.save(retrievedUser);


        assertEquals("updated@example.com", updatedUser.getEmail());


        entityManager.flush();
        entityManager.clear();

        Optional<User> foundUser = userRepository.findById(updatedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals("updated@example.com", foundUser.get().getEmail());
    }

    @Test
    void deleteUserTest() {

        User user = new User();
        user.setEmail("userToDelete@example.com");
        userRepository.save(user);


        userRepository.delete(user);


        assertFalse(userRepository.existsById(user.getId()));
    }

    @Test
    void getAllUsersTest() {

        User user1 = new User();
        user1.setEmail("user1@example.com");
        userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("user2@example.com");
        userRepository.save(user2);


        List<User> allUsers = userRepository.findAll();


        assertEquals(2, allUsers.size());
        assertTrue(allUsers.stream().anyMatch(u -> u.getEmail().equals("user1@example.com")));
        assertTrue(allUsers.stream().anyMatch(u -> u.getEmail().equals("user2@example.com")));
    }


}
