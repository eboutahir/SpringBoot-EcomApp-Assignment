package Ecommerce.ecommerce.Controllers;

import Ecommerce.ecommerce.Models.Category;
import Ecommerce.ecommerce.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/Add")
    //@PreAuthorize("hasRole('ADMIN')")
    public Category addCategory (@RequestBody Category category ) {
        System.out.println("Received request to add category: " + category);
        return categoryService.addCategory (category);
    }

    @GetMapping("/getById/{id}")
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Optional<Category> getById (@PathVariable("id") Integer id ) {
        return categoryService.getById (id);
    }

    @GetMapping("/getAll")
    //@PreAuthorize ("hasAnyRole('USER', 'ADMIN')")
    public List<Category> getAll ( ) {
        return categoryService.getAll ();
    }

}
