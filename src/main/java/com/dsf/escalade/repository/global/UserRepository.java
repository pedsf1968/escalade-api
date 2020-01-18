package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
   User getOne(Integer id);
   User findByLastName(String lastName);
   User findByEmail(String email);
   User findByAlias(String alias);

}
