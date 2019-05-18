package com.example.userportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyUsedException extends RuntimeException {

  public EmailAlreadyUsedException() {
    super("Email is already in use!");
  }
}
