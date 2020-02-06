package com.dsf.escalade.service.global;

import com.dsf.escalade.model.global.Role;
import com.dsf.escalade.model.global.User;
import com.dsf.escalade.repository.global.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

   private final UserRepository userRepository;

   @Autowired
   public UserDetailsServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   @Transactional(readOnly = true)
   public UserDetails loadUserByUsername(String email) {
      User user = userRepository.findByEmail(email);

      if (user == null) throw new UsernameNotFoundException("User not found with email :" + email);

      Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

      for (Role role : user.getRoles()){
         grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
      }

      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
   }

}