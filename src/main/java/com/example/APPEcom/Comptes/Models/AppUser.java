package com.example.APPEcom.Comptes.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
@Getter
@Setter
public class AppUser implements UserDetails {
    private String password;

    private  String username;

    private  Set<GrantedAuthority> authorities;

    private  boolean accountNonExpired;

    private  boolean accountNonLocked;

    private  boolean credentialsNonExpired;

    private  boolean enabled;
}
