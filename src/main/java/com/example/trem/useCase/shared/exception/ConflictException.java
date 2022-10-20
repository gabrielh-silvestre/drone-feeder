package com.example.trem.useCase.shared.exception;

public class ConflictException extends RuntimeException {

  public ConflictException(String message) {
    super(message);
  }

}
