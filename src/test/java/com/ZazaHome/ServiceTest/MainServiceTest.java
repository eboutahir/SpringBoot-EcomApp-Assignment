package com.ZazaHome.ServiceTest;

import com.Zazahome.daos.CategoryRepository;
import com.Zazahome.daos.CommentRepository;
import com.Zazahome.daos.ProductRepository;
import com.Zazahome.daos.UnbanRequestRepository;
import com.Zazahome.daos.UserRepository;
import com.Zazahome.entities.*;
import com.Zazahome.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import java.util.Collections;
import java.util.List;

@Service
public class MainServiceTest {

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
        if (result.hasErrors()) {
            redir.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redir.addFlashAttribute("user", user);
            redir.addFlashAttribute("confirmPassword", confirmPassword);
            redir.addFlashAttribute("role", role);
            redir.addFlashAttribute("title", "Register | Zazahome");
            return "redirect:/register";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            redir.addFlashAttribute("passwordMismatch", true);
            redir.addFlashAttribute("user", user);
            redir.addFlashAttribute("confirmPassword", confirmPassword);
            redir.addFlashAttribute("role", role);
            redir.addFlashAttribute("title", "Register | Zazahome");
            return "redirect:/register";
        }

        User existingUser = this.userRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            redir.addFlashAttribute("emailExists", true);
            redir.addFlashAttribute("user", user);
            redir.addFlashAttribute("confirmPassword", confirmPassword);
            redir.addFlashAttribute("role", role);
            redir.addFlashAttribute("title", "Register | Zazahome");
            return "redirect:/register";
        }

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepo.save(user);
        httpSession.setAttribute("status", "registration-success");
        return "redirect:/login";
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
        m.addAttribute("title", "Unban Request | Zazahome");
        return "unban_request";
    }

    public String processUnbanRequest(UnbanRequest unbanRequest, BindingResult result, HttpSession httpSession) {

        return null;
    }

    public String showProfile(Model m, Principal principal, HttpSession httpSession) {
        User user = this.userRepo.loadUserByUserName(principal.getName());
        List<Product> products = this.productRepo.getSellerAllProducts(user.getId());
        m.addAttribute("title", "Profile | Zazahome");
        m.addAttribute("user", user);
        m.addAttribute("products", products);
        return "profile";
    }


    public <Cart> String checkOut(Model m, Principal principal, HttpSession httpSession) {
        if (principal == null) {
            return "redirect:/login";
        }
        Cart cart = (Cart) httpSession.getAttribute("cart");

        if ((cart == null)) {
            httpSession.setAttribute("checkoutStatus", "emptyCart");
            return "redirect:/cart";
        }
        httpSession.setAttribute("checkoutStatus", "success");

        return "redirect:/checkout/success";
    }

    public String showProductDetail(Integer id, Model m, Principal principal) {
        SellerServiceTest productService = null;
        Product product = productService.getProductById(id);
        m.addAttribute("product", product);

        return "productDetailPage";
    }

    public String processComment(HttpSession httpSession, String comment, Integer userId, Integer productId) {

        comment.stripIndent();

        return "redirect:/product/" + productId;
    }

    public String addCategory(Category category, RedirectAttributes redirectAttribute, HttpSession httpSession) {
        try {
            MainService.saveCategory(category);
            redirectAttribute.addFlashAttribute("status", "category-added");
        } catch (DataIntegrityViolationException e) {

            redirectAttribute.addFlashAttribute("status", "category-already-exists");
        } catch (Exception e) {

            redirectAttribute.addFlashAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        return "redirect:/admin/categories";
    }


    public String updateCategory(Category category, RedirectAttributes redirectAttribute, HttpSession httpSession) {
        try {

            MainServiceTest CategoryService = null;
            CategoryService.updateCategory(category);
            redirectAttribute.addFlashAttribute("status", "category-updated");
        } catch (Exception e) {

            redirectAttribute.addFlashAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        return "redirect:/admin/categories";
    }

    private void updateCategory(Category category) {
    }

    public String deleteCategory(Integer categoryId, RedirectAttributes redirectAttribute, HttpSession httpSession) {
        try {
            MainService.deleteCategory(categoryId);
            redirectAttribute.addFlashAttribute("status", "category-deleted");
        } catch (Exception e) {
            // Handle exceptions
            redirectAttribute.addFlashAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        return "redirect:/admin/categories";
    }

    public String addProduct(Product product, List<String> images, Integer selectedProductCategory,
                             HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            MainService.saveProduct(product, images, selectedProductCategory);
            redirectAttribute.addFlashAttribute("status", "product-added");
        } catch (Exception e) {

            redirectAttribute.addFlashAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        return "redirect:/admin/products";
    }

    public String updateProductDetails(Product product, Integer selectedProductCategory,
                                       HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            MainServiceTest ProductService = null;
            ProductService.updateProductDetails(product, selectedProductCategory);
            redirectAttribute.addFlashAttribute("status", "product-details-updated");
        } catch (Exception e) {

            redirectAttribute.addFlashAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        return "redirect:/admin/products";
    }

    private void updateProductDetails(Product product, Integer selectedProductCategory) {
    }

    public String deleteProduct(Integer productId, RedirectAttributes redirectAttribute, HttpSession httpSession) {
        try {
            MainService.deleteProduct(productId);
            redirectAttribute.addFlashAttribute("status", "product-deleted");
        } catch (Exception e) {

            redirectAttribute.addFlashAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        return "redirect:/seller/products";
    }

    public String processComment(Integer productId, String comment, HttpSession httpSession, Principal principal) {
        try {
            MainService.processComment(productId, comment, principal.getName());
            httpSession.setAttribute("status", "comment-added");
        } catch (Exception e) {
            // Handle exceptions
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        return "redirect:/product/" + productId;
    }

    public String updateComment(Integer commentId, String updatedComment, HttpSession httpSession, Principal principal) {
        try {
            MainService.updateComment(commentId, updatedComment, principal.getName());
            httpSession.setAttribute("status", "comment-updated");
        } catch (Exception e) {
            // Handle exceptions
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        return "redirect:/user/profile";
    }

    public String deleteComment(Integer commentId, HttpSession httpSession, Principal principal) {
        try {
            MainService.deleteComment(commentId, principal.getName());
            httpSession.setAttribute("status", "comment-deleted");
        } catch (Exception e) {
            // Handle exceptions
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        return "redirect:/user/profile";
    }

    public String updateProfileDetails(User updatedUser, BindingResult result, HttpSession httpSession) {
        try {
            MainService.updateUserDetails(updatedUser);
            httpSession.setAttribute("status", "profile-details-updated");
        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        return "redirect:/user/profile";
    }
    public String changePassword(String currentPassword, String newPassword, String confirmPassword,
                                 HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            MainService.changePassword(currentPassword, newPassword, confirmPassword);
            httpSession.setAttribute("status", "password-changed");
        } catch (Exception e) {
            httpSession.setAttribute("status", "password-change-failed");
            e.printStackTrace();
        }

        return "redirect:/user/profile";
    }

    public String requestUnban(String reason, HttpSession httpSession, RedirectAttributes redirectAttribute) {
        try {
            MainServiceTest UnbanRequestService = null;
            UnbanRequestService.requestUnban(reason);
            httpSession.setAttribute("status", "unban-request-sent");
        } catch (Exception e) {
            httpSession.setAttribute("status", "unban-request-failed");
            e.printStackTrace();
        }

        return "redirect:/user/profile";
    }

    private void requestUnban(String reason) {
    }

    public String processCheckout(Integer[] productIds, Integer[] quantities, HttpSession httpSession) {
        try {
            MainService.processCheckout(productIds, quantities);
            httpSession.setAttribute("status", "checkout-success");
        } catch (Exception e) {

            httpSession.setAttribute("status", "checkout-failed");
            e.printStackTrace();
        }

        return "redirect:/user/profile";
    }

    public List<Comment> getAllComments(Integer productId) {
        try {
            return MainService.getAllComments(productId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
