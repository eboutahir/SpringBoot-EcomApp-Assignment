package com.comm.commApp.Service;



import com.comm.commApp.Model.User;
import com.comm.commApp.repository.UserReository;
import com.comm.commApp.Model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserReository userReository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user=userReository.findUserByEmail(email);
        user.orElseThrow(()->new UsernameNotFoundException("user not found"));
        return user.map(CustomUserDetails::new).get();

    }
}
