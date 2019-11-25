package com.dsf.escalade.service;

import com.dsf.escalade.model.global.Address;
import com.dsf.escalade.model.global.User;
import com.dsf.escalade.repository.global.AddressRepository;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final AddressRepository addressRepository;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.userRepository = userRepository;
      this.addressRepository = addressRepository;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }

   @Override
   public User save(UserDto userDto) {
      User user = new User();

      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      user.setAlias(userDto.getAlias());
      user.setPassword((bCryptPasswordEncoder.encode(userDto.getPassword())));
      user.setEmail(userDto.getEmail());
      user.addRole(userDto.getRole());

      if (userDto.hasAddress()) {
         Address address = new Address();

         address.setStreet1(userDto.getStreet1());
         address.setStreet2(userDto.getStreet2());
         address.setZipCode(userDto.getZipCode());
         address.setCity(userDto.getCity());
         address.setCountry(userDto.getCountry());
         address = addressRepository.save(address);
         user.setAdressId(address.getId());
      }

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