package com.example.userportal.exception;

public class EmailAlreadyUsedException extends BadRequestAlertException {

  public EmailAlreadyUsedException() {
    super(null, "Email is already in use!", "userManagement", "emailexists");
  }
}
