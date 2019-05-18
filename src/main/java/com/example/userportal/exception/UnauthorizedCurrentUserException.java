package com.example.userportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedCurrentUserException extends UnauthorizedUserException {

  public UnauthorizedCurrentUserException() {
    super("Current user is unauthorized");
  }
}
