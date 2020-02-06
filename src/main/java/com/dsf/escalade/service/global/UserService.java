package com.dsf.escalade.service.global;

import com.dsf.escalade.web.dto.UserDto;

public interface UserService {
   UserDto getOne(Integer id);
   UserDto findByLastName(String lastName);
   UserDto findByAlias(String alias);
   UserDto findByEmail(String email);
   Integer save(UserDto userDto);
}