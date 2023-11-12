package com.app_ecomerce.Repository;

import com.app_ecomerce.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<Orders, Integer> {
}
