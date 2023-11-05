package com.app_ecomerce.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class testController {


    @GetMapping("/protected")
    public String helloSecure() {
        return "Hello  protected !";
    }

    @GetMapping("/public")
    public String helloPublic() {
        return "Hello  public!";
    }

    @GetMapping("/user")
    public String helloUser() {
        return "Hello  user!";
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return "Hello ADMIN!";
    }
}
