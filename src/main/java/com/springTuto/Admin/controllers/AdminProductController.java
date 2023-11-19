package com.springTuto.Admin.controllers;

import com.springTuto.account.models.User;
import com.springTuto.account.services.UserService;
import com.springTuto.Admin.models.Category;
import com.springTuto.Admin.models.Product;
import com.springTuto.Admin.dto.ProductDto;
import com.springTuto.Admin.servicies.CategoryService;
import com.springTuto.Admin.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Admin")
public class AdminProductController {
    @Autowired
    private CategoryService categoryService;

    private final ProductService productService;
     private final UserService userService;
    public AdminProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }
    @GetMapping("/index")
    public String indexView(final Model model, Authentication auth,
                            @RequestParam(value="user",required = false, defaultValue = "") String email){
        User user;
        if(!email.equals("")){
             user = userService.getAccountByEmail(email);
        }else{
            String Email = auth.getName();
            user = userService.getAccountByEmail(Email);
        }
        model.addAttribute("products",productService.getAllProducts());
        model.addAttribute("user",user);
        model.addAttribute("productDto",new ProductDto());
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "Admin/index";
    }
    @GetMapping("/addProduct")
    public String addProductView(Model model){
        model.addAttribute("productDto",new ProductDto());
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "Admin/addproduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductDto productDto){
        System.out.println(productDto.toString());
        productService.addProduct(productDto);
        return "redirect:/Admin/index";
    }
    @GetMapping("/delete-product/{id}")
    public String delete(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return "redirect:/Admin/index";
    }

    @GetMapping("/editer-product/{id}")
    public String editView(@PathVariable("id") Long id,final Model model){
        Product product = productService.getProductById(id);
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setName(product.getName());
        productDto.setReduction(product.getReduction());
        productDto.setStock(product.getStock());
        model.addAttribute("product",productDto);
        return "Admin/editProduct";
    }

    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute ProductDto productDto){
        productService.updateProduct(productDto.getId(),productDto);
        return "redirect:/Admin/index";
    }

}
