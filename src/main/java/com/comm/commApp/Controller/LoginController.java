package com.comm.commApp.Controller;



import com.comm.commApp.global.GlobalData;
import com.comm.commApp.repository.RoleRepository;
import com.comm.commApp.repository.UserReository;
import com.comm.commApp.Model.Role;
import com.comm.commApp.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
   @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserReository userReository;
    @Autowired
    RoleRepository roleRepository;
    @GetMapping("login")
    public String login()
    {
        GlobalData.cart.clear();
        return "login";
    }
    @GetMapping("/register")
    public String registerGet()
    {
        return "register";
    }

     @PostMapping("/register")
     public String registerPost(@ModelAttribute("user") User user, HttpServletRequest requist) throws ServletException
     {
           String password =user.getPassword();
           user.setPassword(bCryptPasswordEncoder.encode(password));
           List<Role> roles =new ArrayList<>();
           roles.add(roleRepository.findById(2).get());
           user.setRoles(roles);
           userReository.save(user);
           requist.login(user.getEmail(),password);
           return "redirect:/";
     }
}
