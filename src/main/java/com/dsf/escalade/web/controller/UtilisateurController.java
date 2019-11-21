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
   private UserService userService;

   @Autowired
   private SecurityService securityService;

   @Autowired
   private UtilisateurRepository utilisateurRepository;


   @GetMapping("/enregistrement")
   public String getRegistration(Model model) {
      model.addAttribute("user", new UserDto());

      return "utilisateur/enregistrement";
   }

   @PostMapping("/enregistrement")
   public String postRegistration(@ModelAttribute("user") UserDto user, BindingResult bindingResult) {

      if (bindingResult.hasErrors()) {
         return "utilisateur/enregistrement";
      }

      if (!user.getPassword().equals(user.getMatchingPassword())) {
         return "utilisateur/enregistrement";
      }

      userService.registerNewUserAccount(user);

      securityService.autoLogin(user.getLastName(), bCryptPasswordEncoder.encode(user.getMatchingPassword()));

      return "redirect:/";
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

      utilisateurRepository.save(utilisateur);
      model.addAttribute("utilisateurs", utilisateurRepository.findAll());
      return "index";
   }

   @PostMapping("/update/{id}")
   public String updateUser(@PathVariable("id") Integer id, @Valid Utilisateur utilisateur,
                            BindingResult result, Model model) {
      if (result.hasErrors()) {
         utilisateur.setId(id);
         return "utilisateur-update";
      }

      utilisateurRepository.save(utilisateur);
      model.addAttribute("utilisateurs", utilisateurRepository.findAll());
      return "index";
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }


}
