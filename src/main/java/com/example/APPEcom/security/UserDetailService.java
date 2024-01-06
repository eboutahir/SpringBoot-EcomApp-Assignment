package com.example.APPEcom.security;

import com.example.APPEcom.Comptes.Models.AppUser;
import com.example.APPEcom.Comptes.Models.Role;
import com.example.APPEcom.Comptes.Models.User;
import com.example.APPEcom.Comptes.Repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private CompteRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);
        AppUser userDetails = new AppUser();
        userDetails.setUsername(user.getEmail());
        userDetails.setAccountNonExpired(true);
        userDetails.setPassword(user.getPassword());
        userDetails.setEnabled(true);
        userDetails. setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        userDetails.setAuthorities(authorities);
        return userDetails;
    }


}
