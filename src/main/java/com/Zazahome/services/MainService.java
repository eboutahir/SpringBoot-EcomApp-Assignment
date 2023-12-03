package com.Zazahome.services;

import com.Zazahome.daos.CategoryRepository;
import com.Zazahome.daos.CommentRepository;
import com.Zazahome.daos.ProductRepository;
import com.Zazahome.daos.UnbanRequestRepository;
import com.Zazahome.daos.UserRepository;
import com.Zazahome.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Service
public class MainService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UnbanRequestRepository unbanRequestRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void saveCategory(Category category) {
    }

    public static List<Comment> getAllComments(Integer productId) {
        return null;
    }

    public static void processCheckout(Integer[] productIds, Integer[] quantities) {
    }

    public static void changePassword(String currentPassword, String newPassword, String confirmPassword) {
    }

    public static void updateUserDetails(User updatedUser) {
    }

    public static void deleteProduct(Integer productId) {
    }

    public static void processComment(Integer productId, String comment, String name) {
    }

    public static void saveProduct(Product product, List<String> images, Integer selectedProductCategory) {
    }

    public static void updateComment(Integer commentId, String updatedComment, String name) {
    }

    public static void deleteComment(Integer commentId, String name) {
    }

    public static void deleteCategory(Integer categoryId) {
    }

    public void prepareHomeView(Model m, Principal principal) {
        if (principal != null) {
            m.addAttribute("user", this.userRepo.loadUserByUserName(principal.getName()));
        }
        List<Product> products = this.productRepo.getProducts();
        List<Category> categories = this.categoryRepo.getCategories();
        m.addAttribute("title", "Zazahome | Start Shopping Now!");
        m.addAttribute("categories", categories);
        m.addAttribute("products", products);
    }

    public String showRegisterPage(Model m) {
        m.addAttribute("title", "Register | Zazahome");
        m.addAttribute("user", new User());
        return "register";
    }

    public String processRegistration(User user, BindingResult result, RedirectAttributes redir,
                                      String confirmPassword, String role, Model m, HttpSession httpSession) {
        // Ajoutez votre logique de traitement ici
        // ...
        return "redirect:/register";
    }

    public String showLoginPage(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            User user = this.userRepo.loadUserByUserName(auth.getName());
            if (user.getRole().equals("ROLE_CUSTOMER")) {
                return "redirect:/customer/home";
            }
            if (user.getRole().equals("ROLE_ADMIN")) {
                return "redirect:/admin/home";
            }
            if (user.getRole().equals("ROLE_SELLER")) {
                return "redirect:/seller/home";
            }
        }
        m.addAttribute("title", "Login | Zazahome");
        return "login";
    }

    public String showUnbanRequestView(Model m) {
        return null;
    }

    public String processUnbanRequest(UnbanRequest unbanRequest, BindingResult result, HttpSession httpSession) {
        return null;
    }

    public String showProfile(Model m, Principal principal, HttpSession httpSession) {
        return null;
    }

    public String checkOut(Model m, Principal principal, HttpSession httpSession) {
        return null;
    }

    public String showProductDetail(Integer id, Model m, Principal principal) {
        return null;
    }

    public static String processComment(HttpSession httpSession, String comment, Integer userId, Integer productId) {
        return comment;
    }

    // Ajoutez d'autres méthodes du service si nécessaire
}
