package com.dsf.escalade;

import com.dsf.escalade.model.global.Privilege;
import com.dsf.escalade.model.global.Role;
import com.dsf.escalade.model.global.Utilisateur;
import com.dsf.escalade.repository.global.PrivilegeRepository;
import com.dsf.escalade.repository.global.RoleRepository;
import com.dsf.escalade.repository.global.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;


@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

   boolean alreadySetup = false;

   @Autowired
   private UtilisateurRepository utilisateurRepository;

   @Autowired
   private RoleRepository roleRepository;

   @Autowired
   private PrivilegeRepository privilegeRepository;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
   @Transactional
   public void onApplicationEvent(ContextRefreshedEvent event) {

      if (alreadySetup)
         return;
      Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
      Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

      Collection<Privilege> adminPrivileges = Arrays.asList(
            readPrivilege, writePrivilege);
      createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
      createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

      Role adminRole = roleRepository.findByName("ROLE_ADMIN");
      Utilisateur utilisateur = new Utilisateur();
      utilisateur.setPrenom("Test");
      utilisateur.setNom("Test");
      utilisateur.setMotDePasse(bCryptPasswordEncoder.encode("test"));
      utilisateur.setMail("test@test.com");
      utilisateur.setRoles(Arrays.asList(adminRole));
      utilisateurRepository.save(utilisateur);

      alreadySetup = true;
   }

   @Transactional
   private Privilege createPrivilegeIfNotFound(String name) {

      Privilege privilege = privilegeRepository.findByName(name);
      if (privilege == null) {
         privilege = new Privilege(name);
         privilegeRepository.save(privilege);
      }
      return privilege;
   }

   @Transactional
   private Role createRoleIfNotFound(
         String name, Collection<Privilege> privileges) {

      Role role = roleRepository.findByName(name);
      if (role == null) {
         role = new Role(name);
         role.setPrivileges(privileges);
         roleRepository.save(role);
      }
      return role;
   }
}