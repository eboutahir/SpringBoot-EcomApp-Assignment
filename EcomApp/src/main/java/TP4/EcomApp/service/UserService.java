package TP4.EcomApp.service;

import TP4.EcomApp.entity.User;
import TP4.EcomApp.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

        public User createUser(User user) {
            return userRepository.save(user);
        }

        @Transactional
        public User getUserByName(String username) {
            Optional<User> userOptional = userRepository.findByName(username);

            if (userOptional.isPresent()) {
                return userOptional.get();
            } else {
                throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
            }
        }

        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        public User getUserById(int userId) {
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isPresent()) {
                return userOptional.get();
            } else {
                throw new UsernameNotFoundException("Utilisateur non trouvé avec l'ID : " + userId);
            }
        }

        public User updateUser(int userId, User updatedUser) {
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                user.setPassword(updatedUser.getPassword());
                user.setRoles(updatedUser.getRoles());
                return userRepository.save(user);
            } else {
                throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + userId);
            }
        }

        public void deleteUser(int userId) {
            User existingUser = userRepository.findById(userId).orElse(null);

            if (existingUser!=null) {
                userRepository.deleteById(userId);
            } else {
                throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + userId);
            }
        }
    }



