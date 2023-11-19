package com.springTuto.Admin.repositories;

import com.springTuto.Admin.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
