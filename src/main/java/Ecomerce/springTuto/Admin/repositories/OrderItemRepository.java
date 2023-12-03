package Ecomerce.springTuto.Admin.repositories;

import Ecomerce.springTuto.Admin.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
