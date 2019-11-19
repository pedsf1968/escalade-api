package com.dsf.escalade.service;

import com.dsf.escalade.model.global.Role;
import com.dsf.escalade.model.global.Utilisateur;
import com.dsf.escalade.repository.global.UtilisateurRepository;
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

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
   private UtilisateurRepository utilisateurRepository;

   @Override
   @Transactional(readOnly = true)
   public UserDetails loadUserByUsername(String nom) {
      Utilisateur utilisateur = utilisateurRepository.findByNom(nom);
      if (utilisateur == null) throw new UsernameNotFoundException(nom);

      Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
      for (Role role : utilisateur.getRoles()){
         grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
      }

      return new org.springframework.security.core.userdetails.User(utilisateur.getLogin(), utilisateur.getMotDePasse(), grantedAuthorities);
   }
}