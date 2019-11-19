package com.dsf.escalade.service;

import com.dsf.escalade.model.global.Utilisateur;
import com.dsf.escalade.web.dto.UserDto;
import com.dsf.escalade.web.error.UserAlreadyExistException;

public interface UserService {
   Utilisateur registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

}