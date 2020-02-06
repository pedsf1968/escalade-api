package com.dsf.escalade.web.controller.global;

import com.dsf.escalade.EmailConfiguration;
import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.business.TopoService;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.TopoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.bind.ValidationException;

@Slf4j
@Controller
public class MailController {

   @Autowired
   private Environment environment;

   private final EmailConfiguration emailConfiguration;
   private final TopoService topoService;
   private final UserService userService;
   private  JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
   private SimpleMailMessage mailMessage = new SimpleMailMessage();

   @Autowired
   public MailController(EmailConfiguration emailConfiguration, TopoService topoService, UserService userService) {
      this.emailConfiguration = emailConfiguration;
      this.topoService = topoService;
      this.userService = userService;

      mailSender.setHost(emailConfiguration.getHost());
      mailSender.setPort(emailConfiguration.getPort());
   }

   @PostMapping("/feedback")
   public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult) throws ValidationException {
      if (bindingResult.hasErrors()){
         throw new ValidationException("Feedback is not valid");
      }

      mailSender.setUsername(environment.getProperty("spring.mail.username"));
      mailSender.setPassword(environment.getProperty("spring.mail.password"));

      // Create message
      mailMessage.setFrom(feedback.getEmail());
      mailMessage.setSubject("Demande de réservation de topo");
      mailMessage.setText(feedback.getFeedback());

      // Send message
      mailSender.send(mailMessage);
   }

   @GetMapping("/topo/book/{topoId}")
   public String bookTopo(@PathVariable("topoId") Integer topoId, Model model){
      TopoDto topoDto = topoService.getOne(topoId);
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      mailSender.setUsername(environment.getProperty("spring.mail.username"));
      mailSender.setPassword(environment.getProperty("spring.mail.password"));

      // Create message get
      mailMessage.setFrom(authentication.getName());
      mailMessage.setTo(userService.findByAlias(topoDto.getAliasManager()).getEmail());

      if(topoDto.getStatus().equals(StatusType.AVAILABLE.toString())) {
         if(Boolean.TRUE.equals(topoDto.getStatusAuto())){
            topoDto.setStatus(StatusType.RESERVED.toString());
            mailMessage.setSubject("Réservation de topo");
            mailMessage.setText("Réservation du topo : " + topoId);
         } else {
            topoDto.setStatus(StatusType.REQUESTED.toString());
            mailMessage.setSubject("Demande de réservation de topo");
            mailMessage.setText("Demande de réservation du topo : " + topoId );
         }
         // record the requester Alias in the topo aliasClimber attribute
         topoDto.setAliasClimber(userService.findByEmail(authentication.getName()).getAlias());
         // Send message
         topoService.save(topoDto);
         mailSender.send(mailMessage);
      }

      return PathTable.TOPO_ALL_R;
   }

   @GetMapping("/topo/book/{response}/{topoId}")
   public String acceptBookingTopo(@PathVariable("response") String response,@PathVariable("topoId") Integer topoId, Model model){
      TopoDto topoDto = topoService.getOne(topoId);

      // Create message get
      if(topoDto.getAliasClimber()!=null) {
         mailSender.setUsername(environment.getProperty("spring.mail.username"));
         mailSender.setPassword(environment.getProperty("spring.mail.password"));

         if (response.equals("ACCEPTED")) {
            mailMessage.setFrom(userService.findByAlias(topoDto.getAliasManager()).getEmail());
            mailMessage.setTo(userService.findByAlias(topoDto.getAliasClimber()).getEmail());
            mailMessage.setSubject("Démande de réservation de Topo");
            mailMessage.setText("Réservation du topo : " + topoId + " " + response);
            topoDto.setStatus(StatusType.RESERVED.toString());
         } else if (response.equals("RELEASED")) {
            mailMessage.setFrom(userService.findByAlias(topoDto.getAliasClimber()).getEmail());
            mailMessage.setTo(userService.findByAlias(topoDto.getAliasManager()).getEmail());
            mailMessage.setSubject("Libération de Topo");
            mailMessage.setText("Libération du topo  : " + topoId + " " + response);
            topoDto.setAliasClimber(null);
            topoDto.setStatus(StatusType.AVAILABLE.toString());
         } else if (response.equals("REFUSED")) {
            mailMessage.setFrom(userService.findByAlias(topoDto.getAliasManager()).getEmail());
            mailMessage.setTo(userService.findByAlias(topoDto.getAliasClimber()).getEmail());
            mailMessage.setSubject("Demande de réservation de topo");
            mailMessage.setText("Réservation du topo  : " + topoId + " " + response);
            topoDto.setAliasClimber(null);
            topoDto.setStatus(StatusType.AVAILABLE.toString());
         }
         // Send message
         topoService.save(topoDto);
         mailSender.send(mailMessage);
      }

      return PathTable.TOPO_UPDATE_R + topoId;
   }

}
