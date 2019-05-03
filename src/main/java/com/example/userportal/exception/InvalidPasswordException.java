package com.example.userportal.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InvalidPasswordException extends AbstractThrowableProblem {

  public InvalidPasswordException() {
    super(null, "Incorrect password", Status.BAD_REQUEST);
  }
}
