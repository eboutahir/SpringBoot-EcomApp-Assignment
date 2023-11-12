package com.example.tpEcomm.services;

import com.example.tpEcomm.models.Category;
import com.example.tpEcomm.models.Product;
import com.example.tpEcomm.repositories.CategoryRepository;
import com.example.tpEcomm.repositories.ProductRepository;
import com.example.tpEcomm.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository; // Assuming you have a CategoryRepository

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        String categoryName = product.getCategoryName();
        if (categoryName != null && !categoryName.isEmpty()) {
            Category category = categoryRepository.findByName(categoryName);
            if (category != null) {
                product.setCategory(category);
                return productRepository.save(product);
            } else {
                throw new IllegalArgumentException("Category with name '" + categoryName + "' not found");
            }
        } else {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
    }

    @Override
    public Product update(Long productId, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setPictureUrl(updatedProduct.getPictureUrl());
            existingProduct.setCurrentQuantity(updatedProduct.getCurrentQuantity());
            existingProduct.setCategoryName(updatedProduct.getCategoryName());
            return productRepository.save(existingProduct);
        } else {
            throw new IllegalArgumentException("Product with ID " + productId + " not found");
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProduct(Long id) {
        Product pro=productRepository.getReferenceById(id);
        pro.set_deleted(true);
        productRepository.save(pro);

    }

    @Override
    public void EnableProduct(Long id) {
        Product pro=productRepository.getReferenceById(id);
        pro.set_deleted(false);
        productRepository.save(pro);

    }
}
