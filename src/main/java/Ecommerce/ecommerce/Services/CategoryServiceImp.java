package Ecommerce.ecommerce.Services;

import Ecommerce.ecommerce.Models.Category;
import Ecommerce.ecommerce.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory ( Category category ) {
        System.out.println("Saving category to the database: " + category);
        return categoryRepository.save (category);
    }

    @Override
    public Optional<Category> getById ( Integer id ) {
        return categoryRepository.findById (id);
    }


    @Override
    public List<Category> getAll ( ) {
        return categoryRepository.findAll ();
    }
}
