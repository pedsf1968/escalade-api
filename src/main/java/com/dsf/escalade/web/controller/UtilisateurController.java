package com.dsf.escalade.web.controller;

import com.dsf.escalade.repository.global.UtilisateurRepository;
import com.dsf.escalade.service.SecurityService;
import com.dsf.escalade.service.UserService;
import com.dsf.escalade.validator.UserValidator;
import com.dsf.escalade.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UtilisateurController  {

   @Autowired
   private UserService userService;

   @Autowired
   private SecurityService securityService;

   @Autowired
   private UtilisateurRepository utilisateurRepository;

   @Autowired
   private UserValidator userValidator;


   @GetMapping("/enregistrement")
   public String getRegistration(Model model) {
      model.addAttribute("user", new UserDto());

      return "utilisateur/enregistrement";
   }

   @PostMapping("/enregistrement")
   public String postRegistration(@ModelAttribute("user") UserDto user, BindingResult bindingResult) {

      userValidator.validate(user, bindingResult);

      if (bindingResult.hasErrors()) {
         return "utilisateur/enregistrement";
      }

      userService.save(user);
      securityService.autoLogin(user.getLastName(), user.getMatchingPassword());

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

   @GetMapping("/logout")
   public String logout(Model model, String error, String logout) {


      return "redirect:/";
   }

   @GetMapping({"/", "/home"})
   public String home(Model model) {
      return "home";
   }

}
