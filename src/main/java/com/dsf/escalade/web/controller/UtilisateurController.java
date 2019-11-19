package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.global.Utilisateur;
import com.dsf.escalade.repository.global.UtilisateurRepository;
import com.dsf.escalade.service.SecurityService;
import com.dsf.escalade.service.UserService;
import com.dsf.escalade.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class UtilisateurController  {
   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;
   @Autowired
   private PasswordEncoder passwordEncoder;

   @Autowired
   private UserService userService;

   @Autowired
   private SecurityService securityService;


   @Autowired
   private UtilisateurRepository utitilisateurDao;


   @GetMapping("/enregistrement")
   public String registration(Model model) {
      // model.addAttribute("utilisateur", new Utilisateur());
      model.addAttribute("user", new UserDto());

      log.info("\n\n passwordEncoder : dupont :" + passwordEncoder.encode("dupont"));
      log.info("\n\n bCryptPasswordEncoder : dupont :" + bCryptPasswordEncoder.encode("dupont"));
      log.info("\n\n passwordEncoder : tintin :" + passwordEncoder.encode("tintin"));
      log.info("\n\n bCryptPasswordEncoder : tintin :" + bCryptPasswordEncoder.encode("tintin"));

      return "utilisateur/enregistrement";
   }

   @PostMapping("/enregistrement")
   public String registration(@ModelAttribute("user") UserDto user, BindingResult bindingResult) {
      log.info("\n\n utilisateur"+ user.toString()+"\n\n");

      if (bindingResult.hasErrors()) {
         return "utilisateur/enregistrement";
      }

      if (!user.getPassword().equals(user.getMatchingPassword())) {
         return "utilisateur/enregistrement";
      }

      Utilisateur utilisateur = userService.registerNewUserAccount(user);

      //securityService.autoLogin(utilisateur.getNom(), utilisateur.getConfirmationMotDePasse());

      return "topo/topo-list";
   }

   @GetMapping("/login")
   public String login(Model model, String error, String logout) {
      if (error != null)
         model.addAttribute("error", "Your username and password is invalid.");

      if (logout != null)
         model.addAttribute("message", "You have been logged out successfully.");

      return "login";
   }




   @PostMapping("/adduser")
   public String addUser(@Valid Utilisateur utilisateur, BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "utilisateur-add";
      }

      utitilisateurDao.save(utilisateur);
      model.addAttribute("utilisateurs", utitilisateurDao.findAll());
      return "index";
   }

   @PostMapping("/update/{id}")
   public String updateUser(@PathVariable("id") Long id, @Valid Utilisateur utilisateur,
                            BindingResult result, Model model) {
      if (result.hasErrors()) {
         utilisateur.setId(id);
         return "utilisateur-update";
      }

      utitilisateurDao.save(utilisateur);
      model.addAttribute("utilisateurs", utitilisateurDao.findAll());
      return "index";
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }


}
