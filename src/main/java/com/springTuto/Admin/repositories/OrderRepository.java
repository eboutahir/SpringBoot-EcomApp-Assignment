package com.springTuto.Admin.repositories;

import com.springTuto.Admin.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
