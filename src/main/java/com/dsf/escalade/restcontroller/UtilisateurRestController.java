package com.dsf.escalade.restcontroller;

import com.dsf.escalade.dao.global.UtilisateurDao;
import com.dsf.escalade.model.global.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class UtilisateurRestController {

   @Autowired
   private UtilisateurDao utitilisateurDao;

   @GetMapping("/utilisateur")
   public ModelAndView showForm() {

      return new ModelAndView("utilisateurHome", "utilisateur", new Utilisateur());
   }

   @PostMapping("/utilisateur")
   public String greetingSubmit(@ModelAttribute Utilisateur utilisateur) {
      return "result";
   }

}
