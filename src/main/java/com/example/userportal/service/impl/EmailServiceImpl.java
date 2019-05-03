package com.example.userportal.service.impl;

import com.example.userportal.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailServiceImpl implements EmailService {
  public final JavaMailSender emailSender;

  @Override
  public void sendSimpleMessage(
          String to, String subject, String text) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);

  }

  @Override
  public void sendEmail(String from, String to, String title, String content) {
    MimeMessage mail = emailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mail, true);
      helper.setTo(to);
      helper.setReplyTo(from);
      helper.setFrom(from);
      helper.setSubject(title);
      helper.setText(content, true);

      helper.addInline("logo.jpg", new ClassPathResource("templates/logo.jpg"));

    } catch (MessagingException e) {
      e.printStackTrace();
    }
    emailSender.send(mail);
  }
}
