package net.javaguides.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.javaguides.springboot.model.Produit;
import net.javaguides.springboot.service.ProductService;
import java.util.List;
@Controller
public class ProduitController {

    @Autowired
    private ProductService productService;




    @GetMapping("/produit")
    public String home(Model model) {
        List<Produit> products = productService.getAllProducts();
        model.addAttribute("products", products);
       return "home";
    }









}
