package Ecomerce.springTuto.account.services;

import Ecomerce.springTuto.Admin.exceptions.NotFoundException;
import Ecomerce.springTuto.account.models.Role;
import Ecomerce.springTuto.account.models.User;
import Ecomerce.springTuto.account.repositories.AccountRepository;
import Ecomerce.springTuto.account.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(AccountRepository accountRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createAccount(User accountEntity) {
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        User account = new User(accountEntity.getFirstName(),accountEntity.getLasttName(), passwordEncoder.encode(accountEntity.getPassword())
                , accountEntity.getEmail(),roles);
        return accountRepository.save(account);
    }

    public User getAccount(long id) throws NotFoundException {
        return accountRepository.findById(id).orElse(null);
    }

    public List<User> getAccounts() {
        return accountRepository.findAll();
    }

    public User updateAccount(long id, User newAcount) throws NotFoundException {
        User account = accountRepository.findById(id).get();
        account.setFirstName(newAcount.getFirstName());
        account.setLasttName(newAcount.getLasttName());
        account.setEmail(newAcount.getEmail());
        account.setPassword(newAcount.getPassword());
        return accountRepository.save(account);
    }
    public void deleteAccount(long id) {

        accountRepository.deleteById(id);
    }
   public User getAccountByEmail(String email){
        return accountRepository.findByEmail(email);
   }
}