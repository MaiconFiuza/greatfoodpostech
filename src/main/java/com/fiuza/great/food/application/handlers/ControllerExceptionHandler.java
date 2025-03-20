package com.fiuza.great.food.application.handlers;

import com.fiuza.great.food.core.dto.errors.CannotUseSameEmailExceptionDto;
import com.fiuza.great.food.core.dto.errors.IncorrectPasswordExceptionDto;
import com.fiuza.great.food.core.dto.errors.NotFoundExceptionDto;
import com.fiuza.great.food.core.dto.errors.NullDataNotNullExceptionDto;
import com.fiuza.great.food.core.exceptions.CannotUseSameEmailException;
import com.fiuza.great.food.core.exceptions.IncorrectPasswordException;
import com.fiuza.great.food.core.exceptions.NotFoundException;
import com.fiuza.great.food.core.exceptions.NullDataNotNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(NullDataNotNullException.class)
  public ResponseEntity<NullDataNotNullExceptionDto> handlerNullDatException(
      NullDataNotNullException exception) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(status.value())
        .body(new NullDataNotNullExceptionDto(exception.getMessage(), status.value()));
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<NotFoundExceptionDto> handlerNotFoundException(
      NotFoundException exception) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    return ResponseEntity.status(status.value())
        .body(new NotFoundExceptionDto(exception.getMessage(), status.value()));
  }

  @ExceptionHandler(IncorrectPasswordException.class)
  public ResponseEntity<IncorrectPasswordExceptionDto> handlerIncorrectPasswordException(
      IncorrectPasswordException exception) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(status.value())
        .body(new IncorrectPasswordExceptionDto(exception.getMessage(), status.value()));
  }

  @ExceptionHandler(CannotUseSameEmailException.class)
  public ResponseEntity<CannotUseSameEmailExceptionDto> handlerCannotUseSameEmailException(
      CannotUseSameEmailException exception) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(status.value())
        .body(new CannotUseSameEmailExceptionDto(exception.getMessage(), status.value()));
  }
}
