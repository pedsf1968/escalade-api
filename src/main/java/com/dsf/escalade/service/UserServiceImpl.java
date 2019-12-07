package com.dsf.escalade.service;

import com.dsf.escalade.model.global.Role;
import com.dsf.escalade.model.global.User;
import com.dsf.escalade.repository.global.AddressRepository;
import com.dsf.escalade.repository.global.RoleRepository;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   private final AddressRepository addressRepository;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AddressRepository addressRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
      this.addressRepository = addressRepository;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }

   @Override
   public UserDto getOne(Integer id) {
      User user = userRepository.getOne(id);
      UserDto userDto = new UserDto();

      userDto.setId(user.getId());
      userDto.setFirstName(user.getFirstName());
      userDto.setLastName(user.getLastName());
      userDto.setAlias(user.getAlias());
      userDto.setEmail(user.getEmail());
      userDto.setPassword(user.getPassword());
      userDto.setMatchingPassword(user.getPassword());
      userDto.setPhone(user.getPhone());
      userDto.setAddressId(user.getAddressId());

      if( user.getRoles()!= null) {
         List<String> roles = new ArrayList<>();

         for (Role role : user.getRoles()) {
            roles.add(role.getName());
         }

         userDto.setRoles(roles);
      }

      return userDto;
   }

   @Override
   public UserDto findByLastName(String lastName) {
      User user = userRepository.findByLastName(lastName);
      UserDto userDto = new UserDto();

      userDto.setId(user.getId());
      userDto.setFirstName(user.getFirstName());
      userDto.setLastName(user.getLastName());
      userDto.setAlias(user.getAlias());
      userDto.setEmail(user.getEmail());
      userDto.setPassword(user.getPassword());
      userDto.setMatchingPassword(user.getPassword());
      userDto.setPhone(user.getPhone());
      userDto.setAddressId(user.getAddressId());

      if( user.getRoles()!= null) {
         List<String> roles = new ArrayList<>();

         for (Role role : user.getRoles()) {
            roles.add(role.getName());
         }

         userDto.setRoles(roles);
      }

      return userDto;
   }

   @Override
   public UserDto findByAlias(String alias) {
      User user = userRepository.findByAlias(alias);
      UserDto userDto = new UserDto();

      userDto.setId(user.getId());
      userDto.setFirstName(user.getFirstName());
      userDto.setLastName(user.getLastName());
      userDto.setAlias(user.getAlias());
      userDto.setEmail(user.getEmail());
      userDto.setPassword(user.getPassword());
      userDto.setMatchingPassword(user.getPassword());
      userDto.setPhone(user.getPhone());
      userDto.setAddressId(user.getAddressId());

      if( user.getRoles()!= null) {
         List<String> roles = new ArrayList<>();

         for (Role role : user.getRoles()) {
            roles.add(role.getName());
         }

         userDto.setRoles(roles);
      }

      return userDto;
   }

   @Override
   public UserDto findByEmail(String email) {
      User user = userRepository.findByEmail(email);
      UserDto userDto = new UserDto();

      userDto.setId(user.getId());
      userDto.setFirstName(user.getFirstName());
      userDto.setLastName(user.getLastName());
      userDto.setAlias(user.getAlias());
      userDto.setEmail(user.getEmail());
      userDto.setPassword(user.getPassword());
      userDto.setMatchingPassword(user.getPassword());
      userDto.setPhone(user.getPhone());
      userDto.setAddressId(user.getAddressId());

      if( user.getRoles()!= null) {
         List<String> roles = new ArrayList<>();

         for (Role role : user.getRoles()) {
            roles.add(role.getName());
         }

         userDto.setRoles(roles);
      }

      return userDto;
   }

   @Override
   public Integer save(UserDto userDto) {
      User user = new User();

      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      user.setAlias(userDto.getAlias());
      user.setPassword((bCryptPasswordEncoder.encode(userDto.getPassword())));
      user.setEmail(userDto.getEmail());
      user.setAdressId(userDto.getAddressId());

      if( userDto.getRoles()!= null) {
         Set<Role> roles = new HashSet<>();

         for (String role : userDto.getRoles()) {
            roles.add(roleRepository.findByName(role));
         }

         user.setRoles(roles);
      }

      return userRepository.save(user).getId();
   }

   private boolean emailExists(final String email) {
      return userRepository.findByEmail(email) != null;
   }

   private boolean aliasExists(final String alias) { return userRepository.findByAlias(alias) != null;}

   private boolean lastNameExists(final String lastName) { return userRepository.findByLastName(lastName) != null;}
}