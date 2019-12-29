package com.dsf.escalade.web.controller.global;

import com.dsf.escalade.service.global.AddressService;
import com.dsf.escalade.service.global.SecurityServiceImpl;
import com.dsf.escalade.service.global.UserServiceImpl;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.AddressDto;
import com.dsf.escalade.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

   private final UserServiceImpl userService;
   private final AddressService addressService;
   private final  SecurityServiceImpl securityService;


   @Autowired
   public UserController(UserServiceImpl userService, AddressService addressService, SecurityServiceImpl securityService) {
      this.userService = userService;
      this.addressService = addressService;
      this.securityService = securityService;
   }

   @GetMapping("/registration")
   public String getRegistration(Model model) {
      model.addAttribute("userDto", new UserDto());
      model.addAttribute("addressDto", new AddressDto());

      return PathTable.USER_REGISTRATION;
   }

   @PostMapping("/registration")
   public String postRegistration(@ModelAttribute("userDto") @Valid UserDto userDto, @NotNull BindingResult bindingResultUser,
                                  @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NotNull BindingResult bindingResultAddress, Model model) {

      if (bindingResultUser.hasErrors() || bindingResultAddress.hasErrors()) {
         return PathTable.USER_REGISTRATION;
      }

      if(userService.findByEmail(userDto.getEmail())!=null){
         bindingResultUser.rejectValue("email", "5", "Email already exist !");
         return PathTable.USER_REGISTRATION;
      }

      if(userService.findByAlias(userDto.getAlias())!=null){
         bindingResultUser.rejectValue("alias", "6", "Alias already exist !");
         return PathTable.USER_REGISTRATION;
      }

      Integer addressId = addressService.save(addressDto);
      userDto.setAddressId(addressId);

      userDto.setRoles(Lists.newArrayList("ROLE_USER"));

      userService.save(userDto);

      //Le login se fait par l'email
      securityService.autoLogin(userDto.getEmail(), userDto.getMatchingPassword());

      return "redirect:/";
   }

   @GetMapping("/login")
   public String login(Model model, String error, String logout) {

      if (error != null)
         model.addAttribute("error", "Your email and password is invalid.");

      if (logout != null)
         model.addAttribute("message", "You have been logged out successfully.");

      return "login";
   }

   @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication!= null){
         new SecurityContextLogoutHandler().logout(request, response, authentication);
      }

      return "redirect:/login";
   }

   @GetMapping({"/", "/index"})
   public String home(Model model) {
      return "index";
   }

}
