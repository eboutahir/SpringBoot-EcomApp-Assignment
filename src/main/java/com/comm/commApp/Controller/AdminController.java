package com.comm.commApp.Controller;



import com.comm.commApp.Model.Category;
import com.comm.commApp.Model.Product;
import com.comm.commApp.Service.CategoryService;
import com.comm.commApp.Service.ProductService;
import com.comm.commApp.dto.PrudoctDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @GetMapping("/Admin")
    public String adminHome()
    {
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String adminCategories(Model model)
    {
        model.addAttribute("categories",categoryService.getAllCategory());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String adminCategoriesAdd(Model model)
    {
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String postadminCategoriesAdd(@ModelAttribute("category") Category category)
    {
   categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id)
    {
        categoryService.delCategory(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id,Model model)
    {
        Optional<Category> category=categoryService.getCategoryById(id);
        if(category.isPresent())
        {
            model.addAttribute("category",category.get());
            return "categoriesAdd";
        }else return "404";
    }



    @GetMapping("/admin/products")
    public String getProductsModel(Model model)
    {
           model.addAttribute("products",productService.getAllProduct());
        return "products";
    }


    @GetMapping("/admin/products/add")
    public String productAdd(Model model)
    {
        model.addAttribute("productDTO",new PrudoctDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO")PrudoctDTO prudoctDTO,@RequestParam("productImage") MultipartFile file,@RequestParam("imgName")String imgName)throws IOException
    {
        Product product=new Product();
        product.setId(prudoctDTO.getId());
        product.setName(prudoctDTO.getName());
        product.setCategory(categoryService.getCategoryById(prudoctDTO.getCategoryId()).get());
        product.setPrice(prudoctDTO.getPrice());
        product.setWeight(prudoctDTO.getWeight());
        product.setDescription(prudoctDTO.getDescription());
        String imageUUID ;
        if(!file.isEmpty())
        {
            imageUUID = file.getOriginalFilename();
            Path fileNameandPath= Paths.get(uploadDir,imageUUID);

            Files.write(fileNameandPath,file.getBytes());

        }else
        {
            imageUUID=imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id)
    {
      productService.delProduct(id);
        return "redirect:/admin/products";
    }
     @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id,Model model)
     {
         Product product=productService.getProduct(id).get();
         PrudoctDTO prudoctDTO=new PrudoctDTO();
         prudoctDTO.setId(product.getId());
         prudoctDTO.setName(product.getName());
         prudoctDTO.setCategoryId(product.getCategory().getId());
         prudoctDTO.setPrice(product.getPrice());
         prudoctDTO.setWeight(product.getWeight());
         prudoctDTO.setDescription(product.getDescription());
         prudoctDTO.setImageName(product.getImageName());
         model.addAttribute("categories",categoryService.getAllCategory());
         model.addAttribute("productDTO",prudoctDTO);

      return "productsAdd";
     }



}
