package com.example.demo.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.RegisterModel.Registermodel;
import com.example.demo.RegisterReposetry.RegisterReposetry;
@Service
public class RegisterService {
@Autowired
private RegisterReposetry  repo;



    //crere un utilisateur dans la base de donnée
    public Registermodel createuser(Registermodel user){
    return repo.save(user);

    }
//verifier si email existe
     public boolean chekemail(String email){
     return repo.existsByEmail(email);
}
   public boolean authenticateUser(String email, String password) {
    Registermodel user= repo.findByEmail(email);
    if (user != null && user.getPassword().equals(password)) {
        return true;
    }
    return false; 
}











}
