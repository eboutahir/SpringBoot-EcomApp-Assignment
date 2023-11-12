package com.storewala.services;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.storewala.daos.CategoryRepository;
import com.storewala.daos.ProductRepository;
import com.storewala.daos.UserRepository;
import com.storewala.entities.Category;
import com.storewala.entities.Product;
import com.storewala.entities.User;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductRepository productRepo;

    public User loadUserByUserName(String username) {
        return userRepo.loadUserByUserName(username);
    }

    public List<User> getUsers() {
        return userRepo.getUsers();
    }

    public List<Category> getCategories() {
        return categoryRepo.getCategories();
    }

    public List<Product> getProducts() {
        return productRepo.getProducts();
    }

    public String handleAction(Integer id, String type, String categoryAction, String categoryTitle,
                               HttpSession httpSession) throws IOException {

        // Business logic and data access logic

        if (type != null) {
            // Handle user actions
            if (type.equals("suspend")) {
                // Business logic for suspending user
                User user = userRepo.getById(id);
                user.setEnable(false);
                userRepo.save(user);
                httpSession.setAttribute("status", "suspended");
                return "userSuspendById=" + id;
            } else if (type.equals("unsuspend")) {
                // Business logic for unsuspending user
                User user = userRepo.getById(id);
                user.setEnable(true);
                userRepo.save(user);
                httpSession.setAttribute("status", "unsuspended");
                return "userUnsuspendById=" + id;
            } else if (type.equals("delete")) {
                // Business logic for deleting user
                User user = userRepo.getById(id);
                userRepo.delete(user);
                httpSession.setAttribute("status", "user-deleted");
                return "userDeleteById=" + id;
            }
        }

        try {
            if ("addNew".equals(categoryAction)) {
                // Business logic for adding a new category
                if (categoryTitle == null || categoryTitle.isBlank()) {
                    httpSession.setAttribute("status", "title-null");
                    return "";
                }

                Category category = new Category();
                category.setTitle(categoryTitle);

                categoryRepo.save(category);
                httpSession.setAttribute("status", "category-added");
                return "";
            }
        } catch (DataIntegrityViolationException e) {
            // Handle exception
            httpSession.setAttribute("status", "category-already-exist");
            return "";
        }

        // Default case
        httpSession.setAttribute("status", "went-wrong");
        return "";
    }
}
