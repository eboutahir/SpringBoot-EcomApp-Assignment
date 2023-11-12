package com.TP3.Ecommerce.service;
import com.TP3.Ecommerce.entity.user;
import com.TP3.Ecommerce.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userServices implements UserDetailsService {
    @Autowired
    private  userRepository userRepo;

    public userServices(userRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user user1 = userRepo.findByUsername(username);

        if (user1 == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDetails userDetails = User.withUsername(user1.getUsername())
                .password(user1.getPassword())
                .build();

        return userDetails;
    }
}
