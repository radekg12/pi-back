package com.example.userportal.service;

public interface EmailService {
  void sendSimpleMessage(String to, String subject, String content);

  void sendEmail(String from, String to, String title, String content);
}
