package com.fiuza.great.food.core.exceptions;

public class CannotUseSameEmailException extends RuntimeException {

  public CannotUseSameEmailException(String message) {
    super(message);
  }
}
