package com.dsf.escalade.web.controller.global;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.business.TopoService;
import com.dsf.escalade.service.global.EmailService;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.TopoDto;
import com.dsf.escalade.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.MessagingException;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
@Controller
public class MailController {

   private final EmailService emailService;
   private final TopoService topoService;
   private final UserService userService;

   @Autowired
   public MailController(EmailService emailService, TopoService topoService, UserService userService) {
      this.emailService = emailService;
      this.topoService = topoService;
      this.userService = userService;
   }


   @GetMapping("/topo/request/{topoId}")
   public String requestTopo(@PathVariable("topoId") Integer topoId, Locale locale) throws MessagingException {
      TopoDto topoDto = topoService.getOne(topoId);
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDto manager = userService.findByAlias(topoDto.getAliasManager());
      UserDto climber = userService.findByEmail(authentication.getName());

      ResourceBundle resourceBundle = ResourceBundle.getBundle("locale.MailMessages", locale);
      String mailSubject = resourceBundle.getString("mail.subject.request");
      String managerMailContent;
      String climberMailContent;

      if(topoDto.getStatus().equals(StatusType.AVAILABLE.toString())) {
         if(Boolean.TRUE.equals(topoDto.getStatusAuto())){
            // Automatic response
            topoDto.setStatus(StatusType.RESERVED.toString());
            managerMailContent = resourceBundle.getString("mail.response.manager.ok");
            climberMailContent = resourceBundle.getString("mail.response.climber.ok");
         } else {
            // Wait response from topo's manager
            topoDto.setStatus(StatusType.REQUESTED.toString());
            managerMailContent = resourceBundle.getString("mail.request.manager");
            climberMailContent = resourceBundle.getString("mail.request.climber");
         }
         // record the requester Alias in the topo aliasClimber attribute
         topoDto.setAliasClimber(climber.getAlias());
         topoService.save(topoDto);

         // send mail to the manager
         this.emailService.sendMail(topoDto.getName(), manager, climber.getAlias(),mailSubject, managerMailContent, locale);
         // send mail to the climber
         this.emailService.sendMail(topoDto.getName(), climber, manager.getAlias(),mailSubject, climberMailContent, locale);
      }

      return PathTable.TOPO_ALL_R;
   }


   @GetMapping("/topo/response/{response}/{topoId}")
   public String responseTopo(@PathVariable("response") String response,@PathVariable("topoId") Integer topoId, Locale locale) throws MessagingException {
      TopoDto topoDto = topoService.getOne(topoId);
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDto manager = userService.findByAlias(topoDto.getAliasManager());
      UserDto climber = userService.findByEmail(authentication.getName());

      ResourceBundle resourceBundle = ResourceBundle.getBundle("locale.MailMessages", locale);
      String mailSubject = resourceBundle.getString("mail.subject.response");
      String managerMailContent = null;
      String climberMailContent = null;

      // Create message get
      if(topoDto.getAliasClimber()!=null) {
         if (response.equals("ACCEPTED")) {
            topoDto.setStatus(StatusType.RESERVED.toString());
            managerMailContent = resourceBundle.getString("mail.response.manager.ok");
            climberMailContent = resourceBundle.getString("mail.response.climber.ok");
         } else if (response.equals("RELEASED")) {
            topoDto.setAliasClimber(null);
            topoDto.setStatus(StatusType.AVAILABLE.toString());
            managerMailContent = resourceBundle.getString("mail.release.manager");
            climberMailContent = resourceBundle.getString("mail.release.climber");
         } else if (response.equals("REFUSED")) {
            topoDto.setAliasClimber(null);
            topoDto.setStatus(StatusType.AVAILABLE.toString());
            managerMailContent = resourceBundle.getString("mail.response.manager.ko");
            climberMailContent = resourceBundle.getString("mail.response.climber.ko");
         }
         // Send message
         topoService.save(topoDto);

         // send mail to the manager
         this.emailService.sendMail(topoDto.getName(), manager, climber.getAlias(),mailSubject, managerMailContent, locale);
         // send mail to the climber
         this.emailService.sendMail(topoDto.getName(), climber, manager.getAlias(),mailSubject, climberMailContent, locale);
      }

      return PathTable.TOPO_UPDATE_R + topoId;
   }

}
