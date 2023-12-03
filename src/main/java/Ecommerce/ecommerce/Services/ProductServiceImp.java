package Ecommerce.ecommerce.Services;


import Ecommerce.ecommerce.Models.Category;
import Ecommerce.ecommerce.Models.Product;
import Ecommerce.ecommerce.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Override
    @Transactional
    public Product addProduct(Product product) {
        // Check if the associated Category is already in the database
        Category existingCategory = categoryService.getById(product.getCategory().getId()).orElse(null);

        if (existingCategory != null) {
            // If it exists, associate the existing Category with the Product
            product.setCategory(existingCategory);
        } else {
            // If not, save the Category first
            categoryService.addCategory(product.getCategory());
        }

        // Now you can persist the Product
        return productRepository.save(product);
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Product> getById ( Integer id ) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional (readOnly = true)
    public List<Product> getAll ( ) {
        return productRepository.findAll();
    }
}



