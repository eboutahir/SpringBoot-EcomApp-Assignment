package com.example.APPEcom.Comptes.Services;


import com.example.APPEcom.Comptes.Models.Role;
import com.example.APPEcom.Comptes.Models.User;
import com.example.APPEcom.Comptes.Repositories.CompteRepository;
import com.example.APPEcom.Comptes.Repositories.RoleRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompteService {
    private final CompteRepository compteRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CompteService(CompteRepository accountRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.compteRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createAccount(User accountEntity) {
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        User account = new User(accountEntity.getFirstName(),accountEntity.getLasttName(), passwordEncoder.encode(accountEntity.getPassword())
                , accountEntity.getEmail(),roles);
        return compteRepository.save(account);
    }

    public User getAccount(long id) throws ChangeSetPersister.NotFoundException {
        return compteRepository.findById(id).orElse(null);
    }

    public List<User> getAccounts() {
        return compteRepository.findAll();
    }

    public User updateAccount(long id, User newAcount) throws ChangeSetPersister.NotFoundException {
        User account = compteRepository.findById(id).get();
        account.setFirstName(newAcount.getFirstName());
        account.setLasttName(newAcount.getLasttName());
        account.setEmail(newAcount.getEmail());
        account.setPassword(newAcount.getPassword());
        return compteRepository.save(account);
    }
    public void deleteAccount(long id) {

        compteRepository.deleteById(id);
    }
    public User getAccountByEmail(String email){
        return compteRepository.findByEmail(email);
    }
}