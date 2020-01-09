package com.dsf.escalade.web.controller.global;

import com.dsf.escalade.EmailConfiguration;
import com.dsf.escalade.Feedback;
import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.business.TopoService;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.TopoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
      mailSender.setUsername(emailConfiguration.getUsername());
      mailSender.setPassword(emailConfiguration.getPassword());
   }

   @PostMapping("/feedback")
   public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult) throws ValidationException {
      if (bindingResult.hasErrors()){
         throw new ValidationException("Feedback is not valid");
      }

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

      // Create message get
      mailMessage.setFrom(authentication.getName());
      mailMessage.setTo(userService.findByAlias(topoDto.getAliasManager()).getEmail());

      if(topoDto.getStatus().equals(StatusType.AVAILABLE.toString())) {
         if(topoDto.getStatusAuto()){
            topoDto.setStatus(StatusType.RESERVED.toString());
            mailMessage.setSubject("Réservation de topo");
            mailMessage.setText("Réservation du topo : " + topoId);
         } else {
            topoDto.setStatus(StatusType.REQUESTED.toString());
            mailMessage.setSubject("Demande de réservation de topo");
            mailMessage.setText("Demande de réservation du topo : " + topoId );
         }
         // Send message
         topoService.save(topoDto);
         mailSender.send(mailMessage);
      }


      return PathTable.TOPO_ALL_R;
   }
}
