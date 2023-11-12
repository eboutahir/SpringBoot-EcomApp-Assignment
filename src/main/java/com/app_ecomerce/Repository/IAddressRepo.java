package com.app_ecomerce.Repository;

import com.app_ecomerce.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAddressRepo extends JpaRepository<Address, Integer> {
}
