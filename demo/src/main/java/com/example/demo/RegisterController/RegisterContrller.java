package com.example.demo.RegisterController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.RegisterModel.Registermodel;
import com.example.demo.RegisterService.RegisterService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterContrller {

@Autowired
private RegisterService registerService;

    @PostMapping("/creteuser")
    public String createuser(@ModelAttribute Registermodel user,HttpSession session){
        boolean f=registerService.chekemail(user.getEmail());

        if(f){session.setAttribute("msg", "email alrydy existe");
       }else{
      Registermodel registermodel=registerService.createuser(user);
      if(registermodel !=null){
    session.setAttribute("msg", "registration sucessfully ");
      }
      else{session.setAttribute("msg", "somthing wrong on server ");}
      }
      
    return "login";
    }
     @PostMapping("/login")
     public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        boolean isAuthenticated = registerService.authenticateUser(email, password);
    
        if (isAuthenticated) {
           System.out.println("sussn");
           return "/home";
        } else {
        
            return "login";
        }
    }

    @GetMapping("/page")
    public String page() {
    return "login"; 
    }
    

   }
