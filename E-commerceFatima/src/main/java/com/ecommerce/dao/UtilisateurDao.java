package com.ecommerce.dao;

import com.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDao extends CrudRepository<User, String> {
}
