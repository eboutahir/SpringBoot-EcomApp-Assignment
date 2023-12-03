package Ecommerce.ecommerce.Services;

import Ecommerce.ecommerce.Models.Cart;
import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart addCategory( Cart cart);
    Optional<Cart> getById( Integer id);

    List<Cart> getAll();
}
