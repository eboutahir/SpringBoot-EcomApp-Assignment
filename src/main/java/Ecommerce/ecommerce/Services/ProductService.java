package Ecommerce.ecommerce.Services;

import Ecommerce.ecommerce.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct( Product product);
    Optional<Product> getById( Integer id);

    List<Product> getAll();
}
