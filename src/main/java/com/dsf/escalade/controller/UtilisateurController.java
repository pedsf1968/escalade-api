package com.dsf.escalade.controller;

import com.dsf.escalade.dao.global.UtilisateurDao;
import com.dsf.escalade.model.global.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class UtilisateurController  {

   @Autowired
   private UtilisateurDao utitilisateurDao;

   @GetMapping("/signup")
   public String showSignUpForm(Utilisateur utilisateur) {
      return "utilisateur-add";
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
   public String updateUser(@PathVariable("id") Integer id, @Valid Utilisateur utilisateur,
                            BindingResult result, Model model) {
      if (result.hasErrors()) {
         utilisateur.setId(id);
         return "utilisateur-update";
      }

      utitilisateurDao.save(utilisateur);
      model.addAttribute("utilisateurs", utitilisateurDao.findAll());
      return "index";
   }
}
