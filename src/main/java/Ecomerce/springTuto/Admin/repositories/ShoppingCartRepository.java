package Ecomerce.springTuto.Admin.repositories;

import Ecomerce.springTuto.Admin.models.ShoppingCart;
import Ecomerce.springTuto.account.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findBySessionToken(String  sessionToken);
    ShoppingCart findByUser(User user);
}