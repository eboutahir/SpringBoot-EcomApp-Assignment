package TP4.EcomApp.Repositories;

import TP4.EcomApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository extends JpaRepository<Role, Long>
{
    boolean existsByName(String name);
}
