package com.storewala.services;

import com.storewala.daos.CategoryRepository;
import com.storewala.daos.CommentRepository;
import com.storewala.daos.ProductRepository;
import com.storewala.daos.UnbanRequestRepository;
import com.storewala.daos.UserRepository;
import com.storewala.entities.Category;
import com.storewala.entities.Comment;
import com.storewala.entities.Product;
import com.storewala.entities.UnbanRequest;
import com.storewala.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    public String processComment(HttpSession httpSession, String comment, Integer userId, Integer productId) {
        return comment;
    }

    // Ajoutez d'autres méthodes du service si nécessaire
}
