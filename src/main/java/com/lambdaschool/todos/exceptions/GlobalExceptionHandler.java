package com.lambdaschool.todos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A single exception handler for all existing controllers
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  /**
   * Handles ResourceNotFoundException.
   *
   * @param ex  The thrown ResourceNotFoundException
   * @param req The current WebRequest
   * @return    A formatted error
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, WebRequest req) {
    Error error = new Error(HttpStatus.NOT_FOUND, ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(error, error.getHttpStatus());
  }
}