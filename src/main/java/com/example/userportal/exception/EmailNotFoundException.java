package com.example.userportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailNotFoundException extends RuntimeException{

  public EmailNotFoundException() {
    super("Email address not registered");
  }
}
