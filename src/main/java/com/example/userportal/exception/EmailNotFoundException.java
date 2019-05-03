package com.example.userportal.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class EmailNotFoundException extends AbstractThrowableProblem {

  public EmailNotFoundException() {
    super(null, "Email address not registered", Status.BAD_REQUEST);
  }
}
