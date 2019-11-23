package com.dsf.escalade.service;

import com.dsf.escalade.model.global.Utilisateur;
import com.dsf.escalade.web.dto.UserDto;

public interface UserService {
   Utilisateur save(UserDto accountDto);

   Utilisateur findByNom(String nom);
}