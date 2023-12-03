package com.comm.commApp.Controller;

import com.comm.commApp.global.GlobalData;
import com.comm.commApp.Model.Product;
import com.comm.commApp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id)
    {
        GlobalData.cart.add(productService.getProduct(id).get());
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String cartGet(Model model)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart",GlobalData.cart);
        return "cart";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index)
    {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model)
    {
     model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
     return "checkout";
    }

}
