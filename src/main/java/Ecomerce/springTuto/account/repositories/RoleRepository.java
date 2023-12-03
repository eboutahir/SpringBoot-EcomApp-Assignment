package Ecomerce.springTuto.account.repositories;

import Ecomerce.springTuto.account.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}