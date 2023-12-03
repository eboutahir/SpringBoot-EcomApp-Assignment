package com.comm.commApp.repository;



import com.comm.commApp.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
;import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String testRole);
}
