package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Role;
import com.dsf.escalade.model.global.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void getOne() {
        Role role = new Role();
        Set<User> users = new HashSet<>();

        role.setName("ROLE_TEST");
        role.setUsers(users);

        role = roleRepository.save(role);
        Role roleGet = roleRepository.getOne(role.getId());

        assertEquals(roleGet.getName(), role.getName());
        assertEquals(roleGet.getId(), role.getId());
        assertEquals(roleGet.getUsers(), users);
    }

    @Test
    void findByName() {
        Role role = new Role();
        Set<User> users = new HashSet<>();
        String roleName = "ROLE_TEST";

        role.setName(roleName);
        role.setUsers(users);

        role = roleRepository.save(role);
        Role roleGet = roleRepository.findByName(roleName);

        assertEquals(roleGet.getName(), role.getName());
        assertEquals(roleGet.getId(), role.getId());
        assertEquals(roleGet.getUsers(), users);

    }
}