package com.springTuto.account.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteViewController {
    @GetMapping("/")
    public String  siteView(){
        return "IndexPage";
    }
}
