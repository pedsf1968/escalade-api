package com.dsf.escalade.service;

import com.dsf.escalade.web.dto.UserDto;

public interface UserService {
   Integer save(UserDto userDto);

   UserDto findByLastName(String lastName);
   UserDto findByAlias(String alias);
   UserDto findByEmail(String email);
}