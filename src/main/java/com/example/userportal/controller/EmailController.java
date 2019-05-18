package com.example.userportal.controller;

import com.example.userportal.service.EmailService;
import com.example.userportal.service.dto.SupportDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({"/support"})
public class EmailController {
  private final EmailService emailService;
  @Value("${spring.mail.username}")
  private String companyMail;

  @Autowired
  public EmailController(EmailService emailService) {
    this.emailService = emailService;
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping
  public String getCompanyMail() {
    return companyMail;
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PostMapping
  public String send(@Valid @RequestBody SupportDTO supportDTO) {

    emailService.sendSupportEmail(supportDTO);

    Gson gson = new GsonBuilder().create();
    return gson.toJson("successful");
  }
}
