package com.example.Application.ECommerce.respositories;

import com.example.Application.ECommerce.models.IceCream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IcecreamRepository extends JpaRepository<IceCream,Long> {
}
