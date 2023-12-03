package Ecommerce.ecommerce.Controllers;

import Ecommerce.ecommerce.Models.Cart;
import Ecommerce.ecommerce.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/addCart")
    public Cart addCategory (@RequestBody Cart cart ) {
        return cartService.addCategory (cart);
    }

    @GetMapping("/getById/{id}")
    public Optional<Cart> getById (@PathVariable("id") Integer id ) {
        return cartService.getById (id);
    }

    @GetMapping("/getAll")
    public List<Cart> getAll ( ) {
        return cartService.getAll ();
    }
}
