package com.dsf.escalade.web.controller.global;

import com.dsf.escalade.EmailConfiguration;
import com.dsf.escalade.Feedback;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
public class MailController {

   private final EmailConfiguration emailConfiguration;

   public MailController(EmailConfiguration emailConfiguration) {
      this.emailConfiguration = emailConfiguration;
   }

   @PostMapping("/feedback")
   public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult) throws ValidationException {
      if (bindingResult.hasErrors()){
         throw new ValidationException("Feedback is not valid");
      }

      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

      mailSender.setHost(emailConfiguration.getHost());
      mailSender.setPort(emailConfiguration.getPort());
      mailSender.setUsername(emailConfiguration.getUsername());
      mailSender.setPassword(emailConfiguration.getPassword());

      // Create message
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setFrom(feedback.getEmail());
      mailMessage.setTo("dsf@escalade.com");
      mailMessage.setSubject("Demande de réservation dfe topo");
      mailMessage.setText(feedback.getFeedback());

      // Send message
      mailSender.send(mailMessage);

   }
}
