package TP4.EcomApp.service;

import TP4.EcomApp.config.RoleAlreadyExistsException;
import TP4.EcomApp.entity.Role;
import TP4.EcomApp.Repositories.RoleRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;


@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new RoleAlreadyExistsException("Role with name " + role.getName() + " already exists.");
        }
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @SneakyThrows
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + roleId));
    }


    public Role updateRole(Long roleId, Role updatedRole) {

        Role role = getRoleById(roleId);


        if (!role.getName().equals(updatedRole.getName()) && roleRepository.existsByName(updatedRole.getName())) {
            throw new RoleAlreadyExistsException("Role with name " + updatedRole.getName() + " already exists.");
        }

        role.setName(updatedRole.getName());
        return roleRepository.save(role);
    }

    public void deleteRole(Long roleId) {

        Role role = getRoleById(roleId);
        roleRepository.delete(role);
    }
}

