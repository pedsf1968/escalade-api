package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
   Role findByName(String name);

   @Override
   void delete(Role role);


}
