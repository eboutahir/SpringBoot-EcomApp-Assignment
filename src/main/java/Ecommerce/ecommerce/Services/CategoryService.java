package Ecommerce.ecommerce.Services;

import Ecommerce.ecommerce.Models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category addCategory( Category category);
    Optional<Category> getById( Integer id);

    List<Category> getAll();
}
