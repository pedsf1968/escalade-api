package com.dsf.escalade.web.controller.global;

import com.dsf.escalade.service.global.AddressService;
import com.dsf.escalade.service.global.SecurityServiceImpl;
import com.dsf.escalade.service.global.UserDetailsServiceImpl;
import com.dsf.escalade.service.global.UserServiceImpl;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.AddressDto;
import com.dsf.escalade.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
   private final BCryptPasswordEncoder bCryptPasswordEncoder;
   private final AuthenticationManager authenticationManager;
   private final UserDetailsServiceImpl userDetailsService;


   @Autowired
   public UserController(UserServiceImpl userService, AddressService addressService, SecurityServiceImpl securityService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
      this.userService = userService;
      this.addressService = addressService;
      this.securityService = securityService;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
      this.authenticationManager = authenticationManager;
      this.userDetailsService = userDetailsService;
   }

   @GetMapping("/login")
   public String login(Model model, String error, String logout) {

      if (error != null)
         model.addAttribute("error", "Your email and password is invalid.");

      if (logout != null)
         model.addAttribute("message", "You have been logged out successfully.");

      return "login";
   }

   @PostMapping("/login")
   public String postLogin(Model model){

      return "redirect:/";
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

   @GetMapping("/user/new")
   public String newUser(Model model) {
      UserDto userDto = new UserDto();
      userDto.setPhotoLink("avatar.png");
      model.addAttribute(PathTable.ATTRIBUTE_USER, userDto);
      model.addAttribute(PathTable.ATTRIBUTE_ADDRESS, new AddressDto());

      log.info("/user/new user : " + userDto);
      return PathTable.USER_ADD;
   }

   @PostMapping("/user/add")
   public String addUser(@ModelAttribute("userDto") @Valid UserDto userDto, @NotNull BindingResult bindingResultUser,
                                  @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NotNull BindingResult bindingResultAddress, Model model) {

      if (bindingResultUser.hasErrors() || bindingResultAddress.hasErrors()) {
         return PathTable.USER_ADD;
      }

      if(userService.findByEmail(userDto.getEmail())!=null){
         bindingResultUser.rejectValue("email", "5", "Email already exist !");
         return PathTable.USER_ADD;
      }

      if(userService.findByAlias(userDto.getAlias())!=null){
         bindingResultUser.rejectValue("alias", "6", "Alias already exist !");
         return PathTable.USER_ADD;
      }

      if(!userDto.getPassword().equals(userDto.getMatchingPassword())){
         bindingResultUser.rejectValue("matchingPassword", "7", "Bad matching password !");
         return PathTable.USER_ADD;
      }

      // save the address and git id for the user
      userDto.setAddressId(addressService.save(addressDto));
      // set default role
      userDto.setRoles(Lists.newArrayList("ROLE_USER"));

      log.info("/user/add user : " + userDto);
      log.info("/user/add address : " + addressDto);

      // encode password
      String pw = userDto.getPassword();
      userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
      userDto.setMatchingPassword(userDto.getPassword());
      Integer userId = userService.save(userDto);

      //Le login se fait par l'email
      //securityService.autoLogin(userDto.getEmail(), userDto.getMatchingPassword());
      securityService.autoLogin(userDto.getEmail(), pw);

      return PathTable.USER_UPDATE_R + userId;
   }

   @GetMapping("/user/edit")
   public String editUser( Model model){
      // get the authentified user and his address
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDto userDto = userService.findByEmail(authentication.getName());

      AddressDto addressDto = addressService.getOne(userDto.getAddressId());
      log.info("/user/edit user : " + userDto);
      log.info("/user/edit address : " + addressDto);

      model.addAttribute(PathTable.ATTRIBUTE_USER, userDto);
      model.addAttribute(PathTable.ATTRIBUTE_ADDRESS, addressDto);

      return PathTable.USER_UPDATE;
   }


   @GetMapping("/user/edit/{userId}")
   public String editOtherUser( @PathVariable("userId") Integer userId, Model model){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      // get the authentified user and his address
      UserDto userDto = userService.getOne(userId);
      UserDto operator = userService.findByEmail(authentication.getName());

      if (userDto.equals(operator) || operator.getRoles().contains("ROLE_ADMIN")){
         AddressDto addressDto = addressService.getOne(userDto.getAddressId());
         log.info("/user/edit user : " + userDto);
         log.info("/user/edit address : " + addressDto);

         model.addAttribute(PathTable.ATTRIBUTE_USER, userDto);
         model.addAttribute(PathTable.ATTRIBUTE_ADDRESS, addressDto);

         return PathTable.USER_UPDATE;
      }

      return "redirect:/";
   }

   @PostMapping("/user/update/{userId}")
   public String updateUser(@PathVariable("userId") Integer userId, @ModelAttribute("userDto") @Valid UserDto userDto, @NotNull BindingResult bindingResultUser,
                            @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NotNull BindingResult bindingResultAddress, Model model) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (bindingResultUser.hasErrors() || bindingResultAddress.hasErrors()) {
         return PathTable.USER_UPDATE;
      }

      // test if the email is valid
      UserDto otherUser = userService.findByEmail(userDto.getEmail());
      if(otherUser!=null){
         if (otherUser.getId()!=userDto.getId()){
            bindingResultUser.rejectValue("email", "5", "Email already exist !");
            return PathTable.USER_UPDATE;
         }
      }


      // test if the alias is valid
      otherUser = userService.findByAlias(userDto.getAlias());
      if(otherUser!=null){
         if (otherUser.getId()!=userDto.getId()) {
            bindingResultUser.rejectValue("alias", "6", "Alias already exist !");
            return PathTable.USER_UPDATE;
         }
      }

      Integer addressId = addressService.save(addressDto);
      userDto.setAddressId(addressId);
      userDto.setRoles(Lists.newArrayList("ROLE_USER"));

      userService.save(userDto);

      return "redirect:/";
   }


   @GetMapping("/user/password/edit/{userId}")
   public String editPassword( @PathVariable("userId") Integer userId, Model model){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      // get the authentified user and his address
      UserDto userDto = userService.getOne(userId);
      UserDto operator = userService.findByEmail(authentication.getName());

      if (userDto.equals(operator) || operator.getRoles().contains("ROLE_ADMIN")){
         log.info("/user/edit user : " + userDto);
         model.addAttribute(PathTable.ATTRIBUTE_USER, userDto);

         return PathTable.USER_UPDATE_PASSWORD;
      }

      return "redirect:/";
   }

   @PostMapping("/user/password/update")
   public String updatePassword( @ModelAttribute("userDto") @Valid UserDto userDto, BindingResult bindingResultUser,
                                 @Param("oldPassword") String oldPassword,
                                 @Param("newPassword") String newPassword,
                                 @Param("newMatchingPassword") String newMatchingPassword, Model model) {

      // verify the matching with old password
      if(!bCryptPasswordEncoder.matches(oldPassword,userDto.getPassword())){
         bindingResultUser.rejectValue("password", "6", "Bad password !");
         return PathTable.USER_UPDATE_PASSWORD;
      }

      // verify matching between two password
      if(!newPassword.equals(newMatchingPassword)){
         bindingResultUser.rejectValue("password", "7", "Bad matching password !");
         return PathTable.USER_UPDATE_PASSWORD;
      }

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      // get the authentified user and his address
      UserDto operator = userService.findByEmail(authentication.getName());

      if (userDto.equals(operator) || operator.getRoles().contains("ROLE_ADMIN")){
         AddressDto addressDto = addressService.getOne(userDto.getAddressId());

         // encode password
         userDto.setPassword(bCryptPasswordEncoder.encode(newPassword));
         userDto.setMatchingPassword(userDto.getPassword());

         userService.save(userDto);
         securityService.autoLogin(userDto.getEmail(), newPassword);

         model.addAttribute(PathTable.ATTRIBUTE_USER, userDto);
         model.addAttribute(PathTable.ATTRIBUTE_ADDRESS, addressDto);

         return PathTable.USER_UPDATE;
      }

      return "redirect:/";
   }


}
