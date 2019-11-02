package com.dsf.escalade.controler;

import com.dsf.escalade.dao.UtilisateurDao;
import com.dsf.escalade.model.global.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UtilisateurControler  {

   @Autowired
   private UtilisateurDao utitilisateurDao;

   @GetMapping(value = "Utilisateur")
   public List<Utilisateur>displayAllUtilisateur(){
      return utitilisateurDao.findAll();
   }


}
