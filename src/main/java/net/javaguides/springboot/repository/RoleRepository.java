package net.javaguides.springboot.repository;

import net.javaguides.springboot.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}
