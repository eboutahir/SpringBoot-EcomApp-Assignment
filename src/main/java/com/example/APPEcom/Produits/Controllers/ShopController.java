package com.example.APPEcom.Produits.Controllers;

import com.example.APPEcom.Comptes.Models.AppUser;
import com.example.APPEcom.Comptes.Models.User;
import com.example.APPEcom.Comptes.Services.CompteService;
import com.example.APPEcom.Produits.Models.ShoppingCart;
import com.example.APPEcom.Produits.Services.ProductService;
import com.example.APPEcom.Produits.Services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/User")
public class ShopController {
    private final ShopService shopService;
    private final CompteService accountService;
    private final ProductService productService;
    @Autowired
    public ShopController(ShopService shopService, CompteService accountService, ProductService productService) {
        this.shopService = shopService;
        this.accountService = accountService;
        this.productService = productService;
    }

    @GetMapping("/index")
    public String userIndexView(Model model,
                                @RequestParam(value = "user", required = false,defaultValue = "") String email) {


        String Email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = accountService.getAccountByEmail(Email);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("user", user);
        model.addAttribute("shop", shopService.getShoppingCartByUser((AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        return "index";
    }
    @GetMapping("/cart")
    public String cartView(Model model, @RequestParam("id") Optional<Long> id, Authentication auth){
        if(id.isPresent()){
            model.addAttribute("shop", shopService.getShoppingCartById(id.get()));
        }else{
            model.addAttribute("shop", shopService.creatEmptyCart((AppUser)auth.getPrincipal()));
        }

        model.addAttribute("user", accountService.getAccountByEmail(auth.getName()));
        return "cart";
    }
    @PostMapping("/add-cart")
    public String addToCart(Model model, Authentication auth, @RequestParam("id") Long id,
                            @RequestParam("quantity") Integer quantity ) {
        shopService.addItem((AppUser) auth.getPrincipal(),id,quantity);
        return "redirect:/User/index";
    }
    @GetMapping("/remove-cartItem/{id}")
    public String addToCart(Model model, Authentication auth, @PathVariable("id") Long id) {
        ShoppingCart shop = shopService.removeItem((AppUser) auth.getPrincipal(),id);
        model.addAttribute("shop", shop);
        model.addAttribute("user", accountService.getAccountByEmail(((AppUser) ((AppUser) auth.getPrincipal())).getUsername()));
        return "cart";
    }

    @GetMapping("/update-cartItem")
    public String updateItemQuantityCart(Model model, Authentication auth, @RequestParam("id") Long id,
                            @RequestParam("quantity") Integer quantity ) {
        ShoppingCart shop = shopService.updateItemQuantity((AppUser) auth.getPrincipal(),id,quantity);
        model.addAttribute("shop", shop);
        System.out.println( ((AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).toString());
        model.addAttribute("user", accountService.getAccountByEmail(((AppUser) ((AppUser) auth.getPrincipal())).getUsername()));
        return "cart";
    }




}
