package com.example.userportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.userportal.*"})
public class UserPortalApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserPortalApplication.class, args);
  }
}
