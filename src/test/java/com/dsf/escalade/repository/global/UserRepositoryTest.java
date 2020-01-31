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
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Test
    void getOne() {
        User user = new User();
        Role role = new Role();
        Set<Role> roles = new HashSet<>();

        roles.add(roleRepository.getOne(2));

        user.setFirstName("Leonardo");
        user.setLastName("Da Vinci");
        user.setPassword("lkjlkjhlkjhlkj");
        user.setEmail("leonardo.davinci@roma.it");
        user.setAlias("Leo");
        user.setAddressId(2);
        user.setMember(Boolean.FALSE);
        user.setPhone("0123456789");
        user.setPhotoLink("mlkmlkmlk");
        user.setRoles(roles);

        user = userRepository.save(user);
        User userGet = userRepository.getOne(user.getId());
        assertEquals(userGet.getId(), user.getId());
        assertEquals(userGet.getAlias(), user.getAlias());
        assertEquals(userGet.getFirstName(), user.getFirstName());
        assertEquals(userGet.getLastName(), user.getLastName());
        assertEquals(userGet.getMember(), user.getMember());
        assertEquals(userGet.getAddressId(), user.getAddressId());
        assertEquals(userGet.getPassword(), user.getPassword());
        assertEquals(userGet.getPhone(), user.getPhone());
        assertEquals(userGet.getAddressId(), user.getAddressId());
        assertEquals(userGet.getPhotoLink(), user.getPhotoLink());
        assertEquals(userGet.getRoles(), user.getRoles());
        assertEquals(userGet.getEmail(), user.getEmail());
        assertEquals(userGet,user);

    }

    @Test
    void findByLastName() {
        User user = new User();
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        String lastName = "Da Vinci";

        roles.add(roleRepository.getOne(2));

        user.setFirstName("Leonardo");
        user.setLastName(lastName);
        user.setPassword("lkjlkjhlkjhlkj");
        user.setEmail("leonardo.davinci@roma.it");
        user.setAlias("Leo");
        user.setAddressId(2);
        user.setMember(Boolean.FALSE);
        user.setPhone("0123456789");
        user.setPhotoLink("mlkmlkmlk");
        user.setRoles(roles);

        user = userRepository.save(user);
        User userGet = userRepository.findByLastName(lastName);
        assertEquals(userGet.getId(), user.getId());
        assertEquals(userGet.getAlias(), user.getAlias());
        assertEquals(userGet.getFirstName(), user.getFirstName());
        assertEquals(userGet.getLastName(), user.getLastName());
        assertEquals(userGet.getMember(), user.getMember());
        assertEquals(userGet.getAddressId(), user.getAddressId());
        assertEquals(userGet.getPassword(), user.getPassword());
        assertEquals(userGet.getPhone(), user.getPhone());
        assertEquals(userGet.getAddressId(), user.getAddressId());
        assertEquals(userGet.getPhotoLink(), user.getPhotoLink());
        assertEquals(userGet.getRoles(), user.getRoles());
        assertEquals(userGet.getEmail(), user.getEmail());
        assertEquals(userGet,user);
    }

    @Test
    void findByEmail() {
        User user = new User();
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        String email = "leonardo.davinci@roma.it";

        roles.add(roleRepository.getOne(2));

        user.setFirstName("Leonardo");
        user.setLastName("Da Vinci");
        user.setPassword("lkjlkjhlkjhlkj");
        user.setEmail(email);
        user.setAlias("Leo");
        user.setAddressId(2);
        user.setMember(Boolean.FALSE);
        user.setPhone("0123456789");
        user.setPhotoLink("mlkmlkmlk");
        user.setRoles(roles);

        user = userRepository.save(user);
        User userGet = userRepository.findByEmail(email);
        assertEquals(userGet.getId(), user.getId());
        assertEquals(userGet.getAlias(), user.getAlias());
        assertEquals(userGet.getFirstName(), user.getFirstName());
        assertEquals(userGet.getLastName(), user.getLastName());
        assertEquals(userGet.getMember(), user.getMember());
        assertEquals(userGet.getAddressId(), user.getAddressId());
        assertEquals(userGet.getPassword(), user.getPassword());
        assertEquals(userGet.getPhone(), user.getPhone());
        assertEquals(userGet.getAddressId(), user.getAddressId());
        assertEquals(userGet.getPhotoLink(), user.getPhotoLink());
        assertEquals(userGet.getRoles(), user.getRoles());
        assertEquals(userGet.getEmail(), user.getEmail());
        assertEquals(userGet,user);
    }

    @Test
    void findByAlias() {
        User user = new User();
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        String alias = "Leo";

        roles.add(roleRepository.getOne(2));

        user.setFirstName("Leonardo");
        user.setLastName("Da Vinci");
        user.setPassword("lkjlkjhlkjhlkj");
        user.setEmail("leonardo.davinci@roma.it");
        user.setAlias(alias);
        user.setAddressId(2);
        user.setMember(Boolean.FALSE);
        user.setPhone("0123456789");
        user.setPhotoLink("mlkmlkmlk");
        user.setRoles(roles);

        user = userRepository.save(user);
        User userGet = userRepository.findByAlias(alias);
        assertEquals(userGet.getId(), user.getId());
        assertEquals(userGet.getAlias(), user.getAlias());
        assertEquals(userGet.getFirstName(), user.getFirstName());
        assertEquals(userGet.getLastName(), user.getLastName());
        assertEquals(userGet.getMember(), user.getMember());
        assertEquals(userGet.getAddressId(), user.getAddressId());
        assertEquals(userGet.getPassword(), user.getPassword());
        assertEquals(userGet.getPhone(), user.getPhone());
        assertEquals(userGet.getAddressId(), user.getAddressId());
        assertEquals(userGet.getPhotoLink(), user.getPhotoLink());
        assertEquals(userGet.getRoles(), user.getRoles());
        assertEquals(userGet.getEmail(), user.getEmail());
        assertEquals(userGet,user);
    }
}