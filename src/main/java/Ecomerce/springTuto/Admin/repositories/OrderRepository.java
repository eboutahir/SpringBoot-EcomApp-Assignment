package Ecomerce.springTuto.Admin.repositories;

import Ecomerce.springTuto.Admin.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
