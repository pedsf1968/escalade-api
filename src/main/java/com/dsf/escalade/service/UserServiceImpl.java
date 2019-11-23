package com.dsf.escalade.service;

import com.dsf.escalade.model.global.User;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.web.dto.UserDto;
import com.dsf.escalade.web.error.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
      private final BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.userRepository = userRepository;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }

   @Override
   public User save(UserDto userDto) {
      if (lastNameExists(userDto.getLastName())) {
         throw new UserAlreadyExistException("Il y a déjà un compte avec ce nom : " + userDto.getLastName());
      }

      if (emailExists(userDto.getEmail())) {
         throw new UserAlreadyExistException("Il y a déjà un compte avec cette adresse : " + userDto.getEmail());
      }

      if (aliasExists(userDto.getAlias())) {
         throw new UserAlreadyExistException("Il y a déjà un compte avec cet alias : " + userDto.getAlias());
      }

      User user = new User();

      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      user.setAlias(userDto.getAlias());
      user.setPassword((bCryptPasswordEncoder.encode(userDto.getPassword())));
      user.setEmail(userDto.getEmail());
      user.addRole(userDto.getRole());

      return userRepository.save(user);
   }

   @Override
   public User findByLastName(String lastName) {
      return userRepository.findByLastName(lastName);
   }

   @Override
   public User findByAlias(String alias) {
      return userRepository.findByAlias(alias);
   }

   @Override
   public User findByEmail(String email) {
      return userRepository.findByEmail(email);
   }

   private boolean emailExists(final String email) {
      return userRepository.findByEmail(email) != null;
   }

   private boolean aliasExists(final String alias) { return userRepository.findByAlias(alias) != null;}

   private boolean lastNameExists(final String lastName) { return userRepository.findByLastName(lastName) != null;}
}