package com.example.APPEcom.Comptes.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String  siteView(){
        return "IndexPage";
    }
}
