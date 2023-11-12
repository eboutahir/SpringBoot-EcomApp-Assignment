package com.app_ecomerce.Repository;

import com.app_ecomerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
}
