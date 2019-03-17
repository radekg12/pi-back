package com.example.userportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = {"com.example.userportal.*"})
public class UserPortalApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(UserPortalApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(UserPortalApplication.class, args);
  }
}
