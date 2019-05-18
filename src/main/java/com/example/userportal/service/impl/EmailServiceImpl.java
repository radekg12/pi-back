package com.example.userportal.service.impl;

import com.example.userportal.service.CustomerService;
import com.example.userportal.service.EmailService;
import com.example.userportal.service.dto.SupportDTO;
import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailServiceImpl implements EmailService {
  @Value("${spring.mail.username}")
  private String mailTo;

  public final JavaMailSender emailSender;
  private final TemplateEngine templateEngine;
  private final CustomerService customerService;

  @Override
  public void sendSupportEmail(SupportDTO supportDTO) {
    MimeMessage mail = emailSender.createMimeMessage();
    String content = createSupportEmailContent(supportDTO);
    String mailFrom = customerService.getCurrentCustomerDTO().getEmail();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mail, true);
      helper.setTo(mailTo);
      helper.setReplyTo(mailFrom);
      helper.setFrom(mailFrom);
      helper.setSubject("Wsparcie HurtPol");
      helper.setText(content, true);

      helper.addInline("logo.jpg", new ClassPathResource("templates/logo.jpg"));

    } catch (MessagingException e) {
      e.printStackTrace();
    }
    emailSender.send(mail);
  }

  private String createSupportEmailContent(SupportDTO supportDTO) {
    Context context = new Context();
    context.setVariable("header", "Nowa prośba wsparcia");
    context.setVariable("title", supportDTO.getMailTitle());
    context.setVariable("description", supportDTO.getMailContent());
    context.setVariable("mailFrom", supportDTO.getMailFrom());
    return templateEngine.process("template", context);
  }
}
