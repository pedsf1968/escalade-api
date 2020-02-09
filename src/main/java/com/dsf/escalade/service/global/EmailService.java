package com.dsf.escalade.service.global;

import com.dsf.escalade.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Locale;

@Service
public class EmailService {
   private static final String EMAIL_TEMPLATE = "email.html";


   @Value("${escalade.mail.noReply}")
   private String ESCALADE_MAIL_NO_REPLY;

   private static final String BACKGROUND_IMAGE = "images/background.png";
   private static final String LOGO_BACKGROUND_IMAGE = "images/logo-background.png";
   private static final String ESCALADE_BANNER = "images/escalade-banner.png";
   private static final String ESCALADE_LOGO = "images/escalade-logo.png";

   private static final String PNG_MIME = "image/png";

   @Autowired
   private JavaMailSender mailSender;

   @Autowired
   private TemplateEngine stringTemplateEngine;


 public void sendMail(final String topoName, final UserDto userDto, final String climber,
                      final String mailSubject, final String mailContent, final Locale locale)
       throws MessagingException {
    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
    final MimeMessageHelper message
          = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");

    // Prepare the evaluation context
    final Context ctx = new Context(locale);
    ctx.setVariable("topoName", topoName);
    ctx.setVariable("name", userDto.getFirstName() + " " + userDto.getLastName());
    ctx.setVariable("climber", climber);
    ctx.setVariable("mailContent", mailContent);
    ctx.setVariable("date", new Date());

    // Create the HTML body using Thymeleaf
    final String output = stringTemplateEngine.process(EMAIL_TEMPLATE, ctx);

    message.setFrom(ESCALADE_MAIL_NO_REPLY);
    message.setTo(userDto.getEmail());
    message.setSubject(mailSubject);
    message.setText(output, true /* isHtml */);

    // Add the inline images, referenced from the HTML code as "cid:image-name"
   message.addInline("background", new ClassPathResource(BACKGROUND_IMAGE), PNG_MIME);
   message.addInline("logo-background", new ClassPathResource(LOGO_BACKGROUND_IMAGE), PNG_MIME);
   message.addInline("escalade-banner", new ClassPathResource(ESCALADE_BANNER), PNG_MIME);
   message.addInline("escalade-logo", new ClassPathResource(ESCALADE_LOGO), PNG_MIME);

   // Send mail
   this.mailSender.send(mimeMessage);
 }



}
