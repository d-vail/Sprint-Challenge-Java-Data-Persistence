package com.lambdaschool.todos.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * A single exception handler for all existing controllers
 */
//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  /**
   * Handles ConstraintViolationExceptions thrown when data validation fails.
   *
   * @param ex  The thrown ConstraintViolationException
   * @return    A formatted error
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
    List<ConstraintViolation<?>> violations = new ArrayList<>(ex.getConstraintViolations());
    String message = violations.get(0).getMessage();
    Error error = new Error(HttpStatus.BAD_REQUEST, "Validation Error", message);
    return new ResponseEntity<>(error, error.getHttpStatus());
  }

  /**
   * Handle HttpMessageNotReadableExceptions thrown when incoming JSON is malformed.
   *
   * @param ex  The thrown HttpMessageNotReadableException
   * @return    A formatted error
   */
  @Override
  public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                        HttpHeaders headers, HttpStatus status, WebRequest request) {
    Error error = new Error(HttpStatus.BAD_REQUEST, "Malformed JSON request", ex.getMessage());
    return new ResponseEntity<>(error, error.getHttpStatus());
  }

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


  /**
   * Default exception handler for any generic exception thrown.
   *
   * @param ex  The thrown Exception
   * @param req The current WebRequest
   * @return    A formatted error
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleDefaultException(Exception ex, WebRequest req) {
    Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
            req.getDescription(false));
    return new ResponseEntity<>(error, error.getHttpStatus());
  }
}