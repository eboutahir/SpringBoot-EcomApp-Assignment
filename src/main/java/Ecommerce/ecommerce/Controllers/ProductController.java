package Ecommerce.ecommerce.Controllers;

import Ecommerce.ecommerce.Models.Product;
import Ecommerce.ecommerce.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product addProduct (@RequestBody Product product ) {
        return productService.addProduct (product);
    }

    @GetMapping("/getById/{id}")
    public Optional<Product> getById (@PathVariable("id") Integer id ) {
        return productService.getById (id);
    }

    @GetMapping("/getAll")
    public List<Product> getAll ( ) {
        return productService.getAll ();
    }
}
