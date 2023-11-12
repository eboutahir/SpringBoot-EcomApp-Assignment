package com.storewala.services;

import com.storewala.daos.CategoryRepository;
import com.storewala.daos.ProductRepository;
import com.storewala.daos.UserRepository;
import com.storewala.entities.Category;
import com.storewala.entities.Product;
import com.storewala.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SellerService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private UserRepository userRepo;

    static String usingRandomUUID() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("_", "");
    }

    public void prepareSellerHomeView(Model m, Principal principal) {
        User user = this.userRepo.loadUserByUserName(principal.getName());
        List<Product> sellerProducts = this.productRepo.getSellerAllProducts(user.getId());
        m.addAttribute("title", "Seller Panel | Zazahome");
        m.addAttribute("product", new Product());
        m.addAttribute("categories", categoryRepo.getCategories());
        m.addAttribute("user", user);
        m.addAttribute("products", sellerProducts);
    }

    public String addProduct(Product product, List<MultipartFile> images, Integer selectedProductCategory,
                             HttpSession httpSession, Principal principal, RedirectAttributes redirectAttribute) {
        User user = this.userRepo.loadUserByUserName(principal.getName());

        if (images.size() > 5) {
            httpSession.setAttribute("status", "images-exceed");
            return "redirect:/seller/home";
        }

        List<String> productImages = new ArrayList<>();

        try {
            for (MultipartFile image : images) {
                String imageName = usingRandomUUID() + "_" + image.getOriginalFilename();
                File savedFile = new ClassPathResource("static/product_upload").getFile();
                Path path = Paths.get(savedFile.getAbsolutePath() + File.separator + imageName);
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                productImages.add(imageName);
            }

            product.setSellerId(user.getId());
            product.setSellerName(user.getName());
            product.setPriceAfterDiscount(product.getDiscountPrice());
            product.setCategory(this.categoryRepo.getById(selectedProductCategory));
            product.setImages(productImages);
            this.productRepo.save(product);
            httpSession.setAttribute("status", "product-added");
            return "redirect:/seller/home?productAdded";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
            return "redirect:/seller/home?internalError";
        }
    }
}
