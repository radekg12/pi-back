package com.example.userportal.controller;

import com.example.userportal.service.EmailService;
import com.example.userportal.service.dto.SupportDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;

@RestController
@RequestMapping({"/support"})
public class EmailController {
  private final EmailService emailService;
  private final TemplateEngine templateEngine;
  @Value("${spring.mail.username}")
  private String mail;

  @Autowired
  public EmailController(EmailService emailService,
                         TemplateEngine templateEngine) {
    this.emailService = emailService;
    this.templateEngine = templateEngine;
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping
  public String getMail() {
    Gson gson = new GsonBuilder().create();
    return gson.toJson(mail);
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PostMapping
  public String send(@Valid @RequestBody SupportDTO supportDTO) {
    Context context = new Context();
    context.setVariable("header", "Nowa prośba wsparcia");
    context.setVariable("title", supportDTO.getMailTitle());
    context.setVariable("description", supportDTO.getMailContent());
    context.setVariable("mailFrom", supportDTO.getMailFrom());
    String body = templateEngine.process("template", context);
    emailService.sendEmail(supportDTO.getMailFrom(), mail, "Wsparcie HurtPol", body);

    Gson gson = new GsonBuilder().create();
    return gson.toJson("successful");
  }
}
