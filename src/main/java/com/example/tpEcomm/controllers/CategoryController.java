package com.example.tpEcomm.controllers;

import com.example.tpEcomm.models.Category;
import com.example.tpEcomm.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;
    @GetMapping("/all")
    public List<Category> getAllCategory()
    {
     return service.findAll();
    }
    @GetMapping("/{category_id}")
    public Optional<Category> getCategoryById(@PathVariable("category_id")Long category_id)
    {
        return service.getById(category_id);
    }
    @PostMapping("/add")
    public void AddCategory(@RequestBody Category category)
    {
        service.save(category);
    }
    @PutMapping("/{category_id}")
    public Category updateCategory(@RequestBody Category category)
    {
        return service.update(category);
    }
    @DeleteMapping("/{category_id}")
    public void deleteCategory(@PathVariable("category_id") Long category_id)
    {
        service.deletedById(category_id);
    }








}
