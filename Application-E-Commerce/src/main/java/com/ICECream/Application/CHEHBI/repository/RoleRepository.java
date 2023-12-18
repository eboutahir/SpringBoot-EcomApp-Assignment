package com.ICECream.Application.CHEHBI.repository;

import com.ICECream.Application.CHEHBI.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}
