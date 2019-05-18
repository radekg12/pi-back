package com.example.userportal.service;

import com.example.userportal.service.dto.SupportDTO;

public interface EmailService {
  void sendSupportEmail(SupportDTO supportDTO);
}
