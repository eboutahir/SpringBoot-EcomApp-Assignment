package com.example.tpEcomm.repositories;

import com.example.tpEcomm.models.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orderr,Long> {
}
