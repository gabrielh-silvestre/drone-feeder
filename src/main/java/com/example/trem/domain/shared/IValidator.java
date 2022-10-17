package com.example.trem.domain.shared;

public interface IValidator<T> {

  void validate(T object) throws RuntimeException;

}
