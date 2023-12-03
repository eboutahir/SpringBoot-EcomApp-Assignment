package com.comm.commApp;

import com.comm.commApp.Model.Role;
import com.comm.commApp.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveRoleTest() {

        Role role = new Role();
        role.setName("Test Role");


        Role savedRole = roleRepository.save(role);


        assertEquals(role.getName(), savedRole.getName());
        assertTrue(savedRole.getId() > 0);


        entityManager.flush();
        entityManager.clear();

        Optional<Role> retrievedRole = roleRepository.findById(savedRole.getId());
        assertTrue(retrievedRole.isPresent());
        assertEquals(savedRole.getName(), retrievedRole.get().getName());
    }

    @Test
    void findByNameTest() {

        Role role = new Role();
        role.setName("Test Role");
        roleRepository.save(role);


        Optional<Role> foundRole = roleRepository.findByName("Test Role");


        assertTrue(foundRole.isPresent());
        assertEquals(role.getName(), foundRole.get().getName());
    }

    @Test
    void findByNameNotFoundTest() {

        Optional<Role> foundRole = roleRepository.findByName("Nonexistent Role");


        assertFalse(foundRole.isPresent());
    }

    @Test
    void updateRoleTest() {

        Role role = new Role();
        role.setName("Original Role");
        roleRepository.save(role);


        Role retrievedRole = roleRepository.findByName("Original Role").orElseThrow(() -> new RuntimeException("Role not found"));
        retrievedRole.setName("Updated Role");
        Role updatedRole = roleRepository.save(retrievedRole);


        assertEquals("Updated Role", updatedRole.getName());


        entityManager.flush();
        entityManager.clear();

        Optional<Role> foundRole = roleRepository.findById(updatedRole.getId());
        assertTrue(foundRole.isPresent());
        assertEquals("Updated Role", foundRole.get().getName());
    }

    @Test
    void deleteRoleTest() {
        // Given
        Role role = new Role();
        role.setName("Role to Delete");
        roleRepository.save(role);


        roleRepository.delete(role);


        assertFalse(roleRepository.existsById(role.getId()));
    }

    @Test
    void getAllRolesTest() {

        Role role1 = new Role();
        role1.setName("Role 1");
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setName("Role 2");
        roleRepository.save(role2);


        List<Role> allRoles = roleRepository.findAll();


        assertEquals(2, allRoles.size());
        assertTrue(allRoles.stream().anyMatch(r -> r.getName().equals("Role 1")));
        assertTrue(allRoles.stream().anyMatch(r -> r.getName().equals("Role 2")));
    }


}
