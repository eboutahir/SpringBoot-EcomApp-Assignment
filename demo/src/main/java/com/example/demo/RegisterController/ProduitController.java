package com.example.demo.RegisterController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.RegisterModel.produit;
import com.example.demo.RegisterService.ProduitService;

@Controller
public class ProduitController {
@Autowired
private ProduitService ProduitService;

public ProduitController(ProduitService ProduitService){
 this.ProduitService=ProduitService;   
}

@GetMapping("/produits")
public String  afficheproduit(Model model){
List<produit>produit=ProduitService.getAllproduit();
model.addAttribute("produit", produit);
return "home";
}



}
