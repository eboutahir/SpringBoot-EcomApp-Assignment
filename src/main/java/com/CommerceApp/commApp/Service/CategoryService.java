package com.CommerceApp.commApp.Service;

import com.CommerceApp.commApp.Model.Category;
import com.CommerceApp.commApp.repository.CategoryRepisotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
     CategoryRepisotory categoryRepisotory;
    public List<Category> getAllCategory()
    {
        return categoryRepisotory.findAll();
    }
    public void addCategory(Category category)
    {
        categoryRepisotory.save(category);
    }
    public void delCategory(int id)
    {
        categoryRepisotory.deleteById(id);
    }
   public Optional<Category> getCategoryById(int id)
   {
       return categoryRepisotory.findById(id);
   }




}
