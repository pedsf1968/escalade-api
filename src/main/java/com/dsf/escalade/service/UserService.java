package com.dsf.escalade.service;

import com.dsf.escalade.model.global.User;
import com.dsf.escalade.web.dto.UserDto;

public interface UserService {
   User save(UserDto userDto);

   User findByLastName(String lastName);
   User findByAlias(String alias);
   User findByEmail(String email);
}