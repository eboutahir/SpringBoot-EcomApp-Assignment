package com.Zazahome.services;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.Zazahome.daos.CategoryRepository;
import com.Zazahome.daos.ProductRepository;
import com.Zazahome.daos.UserRepository;
import com.Zazahome.entities.Category;
import com.Zazahome.entities.Product;
import com.Zazahome.entities.User;

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

        if (type != null) {
            if (type.equals("suspend")) {
                User user = userRepo.getById(id);
                user.setEnable(false);
                userRepo.save(user);
                httpSession.setAttribute("status", "suspended");
                return "userSuspendById=" + id;
            } else if (type.equals("unsuspend")) {
                User user = userRepo.getById(id);
                user.setEnable(true);
                userRepo.save(user);
                httpSession.setAttribute("status", "unsuspended");
                return "userUnsuspendById=" + id;
            } else if (type.equals("delete")) {
                User user = userRepo.getById(id);
                userRepo.delete(user);
                httpSession.setAttribute("status", "user-deleted");
                return "userDeleteById=" + id;
            }
        }

        try {
            if ("addNew".equals(categoryAction)) {
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

            httpSession.setAttribute("status", "category-already-exist");
            return "";
        }

        httpSession.setAttribute("status", "went-wrong");
        return "";
    }
}
