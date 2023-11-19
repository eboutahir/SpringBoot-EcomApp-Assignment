package com.springTuto.account.controllers;

import com.springTuto.account.models.User;
import com.springTuto.account.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
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

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String createAccount(@ModelAttribute User accountEntity, Model model){
       System.out.println(accountEntity.toString());
        accountService.createAccount(accountEntity);
        model.addAttribute("regSucc","you have been registred successfully");
        return "login";
    }

}
