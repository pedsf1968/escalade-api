package com.dsf.escalade.validator;

import com.dsf.escalade.model.global.User;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
   private static final int NAME_LENGTH_MIN = 2;
   private static final int NAME_LENGTH_MAX = 32;
   private static final int PASSWORD_LENGTH_MIN = 2;
   private static final int PASSWORD_LENGTH_MAX = 32;

   @Autowired
   private UserService userService;

   @Override
   public boolean supports(Class<?> aClass) {
      return User.class.equals(aClass);
   }

   @Override
   public void validate(Object o, Errors errors) {
      UserDto userDto = (UserDto) o;

      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");

      if (userDto.getLastName().length() < NAME_LENGTH_MIN || userDto.getLastName().length() > NAME_LENGTH_MAX) {
         errors.rejectValue("lastName", "Size.userForm.username");
      }
      if (userService.findByLastName(userDto.getLastName()) != null) {
         errors.rejectValue("lastName", "Duplicate.userForm.username");
      }

      if (userService.findByEmail(userDto.getEmail())!= null) {
         errors.rejectValue("email", "Duplicate.userForm.email");
      }

      if (userService.findByAlias(userDto.getAlias())!=null) {
         errors.rejectValue("email", "Duplicate.userForm.alias");
      }


      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
      if (userDto.getPassword().length() < PASSWORD_LENGTH_MIN || userDto.getPassword().length()  > PASSWORD_LENGTH_MAX) {
         errors.rejectValue("password", "Size.userForm.password");
      }

      if (!userDto.getMatchingPassword().equals(userDto.getPassword())) {
         errors.rejectValue("matchingPassword", "Diff.userForm.passwordConfirm");
      }
   }
}