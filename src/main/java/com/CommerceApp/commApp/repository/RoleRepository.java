package com.CommerceApp.commApp.repository;

import com.CommerceApp.commApp.Model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
;



public interface RoleRepository extends JpaRepository<Role,Integer> {
}
