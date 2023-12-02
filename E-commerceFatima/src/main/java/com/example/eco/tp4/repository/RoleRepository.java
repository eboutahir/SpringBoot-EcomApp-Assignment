package com.example.eco.tp4.repository;

import org.springframework.data.repository.CrudRepository;

import com.nico.store.store.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}
