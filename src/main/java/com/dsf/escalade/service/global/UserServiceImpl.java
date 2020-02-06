package com.dsf.escalade.service.global;

import com.dsf.escalade.model.global.Role;
import com.dsf.escalade.model.global.User;
import com.dsf.escalade.repository.global.RoleRepository;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service("UserService")
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;

   @Autowired
   public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
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
      userDto.setPhotoLink(user.getPhotoLink());

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

      if(user !=null) {
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
         userDto.setPhotoLink(user.getPhotoLink());

         for (Role role : user.getRoles()) {
            userDto.addRole(role.getName());
         }

         return userDto;
      }

      return null;
   }

   @Override
   public UserDto findByAlias(String alias) {
      User user = userRepository.findByAlias(alias);

      if(user !=null) {
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
         userDto.setPhotoLink(user.getPhotoLink());

         for (Role role : user.getRoles()) {
            userDto.addRole(role.getName());
         }

         return userDto;
      }
      return null;
   }

   @Override
   public UserDto findByEmail(String email) {
      User user = userRepository.findByEmail(email);

      if(user !=null) {
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
         userDto.setPhotoLink(user.getPhotoLink());

         for (Role role : user.getRoles()) {
            userDto.addRole(role.getName());
         }

         return userDto;
      }

      return null;
   }

   @Override
   public Integer save(UserDto userDto) {
      User user = new User();

      user.setId(userDto.getId());
      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      user.setAlias(userDto.getAlias());
      user.setPassword(userDto.getPassword());
      user.setEmail(userDto.getEmail());
      user.setPhone(userDto.getPhone());
      user.setAddressId(userDto.getAddressId());
      user.setPhotoLink(userDto.getPhotoLink());

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