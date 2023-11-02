package com.example.Application.ECommerce.controlles;

import com.example.Application.ECommerce.models.IceCream;
import com.example.Application.ECommerce.respositories.IcecreamRepository;
import com.example.Application.ECommerce.services.IceCreamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class IceCreamController {
    private final IceCreamService iceCreamService;
    IcecreamRepository icecreamRepository;

    public IceCreamController(IceCreamService iceCreamService) {
        this.iceCreamService = iceCreamService;
    }

    @GetMapping("/add_icecream")
    public String showFormAdd(Model model) {


        return "add-Icecream";
    }


    @PostMapping("/addIcesream")
    public String addUser(@Valid IceCream iceCream, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-Icecream";
        }

        icecreamRepository.save(iceCream);
        return "redirect:/product";
    }

}
