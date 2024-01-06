package com.example.APPEcom.Comptes.Controllers;

import com.example.APPEcom.Comptes.Models.User;
import com.example.APPEcom.Comptes.Services.CompteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService= compteService;
    }

    @GetMapping("/login")
    public String  loginView(){
        return "login";
    }

    @GetMapping("/register")
    public String  registerView(Model model){
        model.addAttribute("user",new User());
        return "createAccount";
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String createAccount(@ModelAttribute User accountEntity, Model model){
        System.out.println(accountEntity.toString());
        compteService.createAccount(accountEntity);
        model.addAttribute("regSucc","you have been registred successfully");
        return "login";
    }

}
