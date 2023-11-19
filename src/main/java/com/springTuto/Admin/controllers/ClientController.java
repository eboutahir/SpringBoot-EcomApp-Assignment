package com.springTuto.Admin.controllers;

import com.springTuto.Admin.models.Category;
import com.springTuto.Admin.models.ShoppingCart;
import com.springTuto.Admin.servicies.CategoryService;
import com.springTuto.Admin.servicies.ProductService;
import com.springTuto.Admin.servicies.ShopService;
import com.springTuto.account.models.AppUser;
import com.springTuto.account.models.User;
import com.springTuto.account.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/User")
public class ClientController {
    @Autowired
    private CategoryService categoryService;
    private final ShopService shopService;
    private final UserService accountService;
    private final ProductService productService;
    @Autowired
    public ClientController(ShopService shopService, UserService accountService, ProductService productService) {
        this.shopService = shopService;
        this.accountService = accountService;
        this.productService = productService;
    }

    @GetMapping("/index")
    public String userIndexView(Model model,
                                @RequestParam(value = "user", required = false,defaultValue = "") String email) {


        String Email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = accountService.getAccountByEmail(Email);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("user", user);
        model.addAttribute("shop", shopService.getShoppingCartByUser((AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        return "index";
    }
    @GetMapping("/product/{id}")
    public String userViewPoduct(Model model,
                                @RequestParam(value = "user", required = false,defaultValue = "") String email,@PathVariable("id") Long id) {


        String Email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = accountService.getAccountByEmail(Email);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categories);
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("user", user);
        model.addAttribute("shop", shopService.getShoppingCartByUser((AppUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        return "viewProduct";
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
