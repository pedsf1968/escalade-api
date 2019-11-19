package com.dsf.escalade.service;

import com.dsf.escalade.model.global.Utilisateur;
import com.dsf.escalade.repository.global.RoleRepository;
import com.dsf.escalade.repository.global.UtilisateurRepository;
import com.dsf.escalade.web.dto.UserDto;
import com.dsf.escalade.web.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
   private UtilisateurRepository utilisateurRepository;
   @Autowired
   private RoleRepository roleRepository;
   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;
   @Autowired
   private PasswordEncoder passwordEncoder;

   @Override
   public Utilisateur registerNewUserAccount(final UserDto accountDto) {
      if (emailExists(accountDto.getEmail())) {
         throw new UserAlreadyExistException("Il y a déjà un compte avec cette adresse : " + accountDto.getEmail());
      }
      final Utilisateur utilisateur = new Utilisateur();

      utilisateur.setPrenom(accountDto.getFirstName());
      utilisateur.setNom(accountDto.getLastName());
      utilisateur.setLogin(accountDto.getLogin());
      utilisateur.setMotDePasse((bCryptPasswordEncoder.encode(accountDto.getPassword())));
      utilisateur.setMail(accountDto.getEmail());
      utilisateur.setRoles(asList(roleRepository.findByName("ROLE_USER")));

      return utilisateurRepository.save(utilisateur);
   }

   private boolean emailExists(final String email) {
      return utilisateurRepository.findByMail(email) != null;
   }

}