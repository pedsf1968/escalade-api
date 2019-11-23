package com.dsf.escalade.service;

import com.dsf.escalade.model.global.Civilite;
import com.dsf.escalade.model.global.Utilisateur;
import com.dsf.escalade.repository.global.RoleRepository;
import com.dsf.escalade.repository.global.UtilisateurRepository;
import com.dsf.escalade.web.dto.UserDto;
import com.dsf.escalade.web.error.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

   private final UtilisateurRepository utilisateurRepository;
   private final RoleRepository roleRepository;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   public UserServiceImpl(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.utilisateurRepository = utilisateurRepository;
      this.roleRepository = roleRepository;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }


   @Override
   public Utilisateur save(UserDto accountDto) {
      if (emailExists(accountDto.getEmail())) {
         throw new UserAlreadyExistException("Il y a déjà un compte avec cette adresse : " + accountDto.getEmail());
      }

      Utilisateur utilisateur = new Utilisateur();

      utilisateur.setCivilite(Civilite.M);
      utilisateur.setPrenom(accountDto.getFirstName());
      utilisateur.setNom(accountDto.getLastName());
      utilisateur.setLogin(accountDto.getLogin());
      utilisateur.setMotDePasse((bCryptPasswordEncoder.encode(accountDto.getPassword())));
      utilisateur.setMail(accountDto.getEmail());
      utilisateur.setRoles(new HashSet<>(roleRepository.findAll()));

      return utilisateurRepository.save(utilisateur);
   }

   @Override
   public Utilisateur findByNom(String nom) {
      return utilisateurRepository.findByNom(nom);
   }
   private boolean emailExists(final String email) {
      return utilisateurRepository.findByMail(email) != null;
   }

}