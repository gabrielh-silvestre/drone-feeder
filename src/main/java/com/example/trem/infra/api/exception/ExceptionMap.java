package com.example.trem.infra.api.exception;

public class ExceptionMap {

  private final String message;

  private final String error;

  public ExceptionMap(Exception e) {
    this.message = e.getMessage();
    this.error = e.getClass().getSimpleName();
  }

  public String getMessage() {
    return message;
  }

  public String getError() {
    return error;
  }

}
