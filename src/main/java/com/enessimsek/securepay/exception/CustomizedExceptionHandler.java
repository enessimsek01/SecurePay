package com.enessimsek.securepay.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleDuplicateEmailException(DuplicatedEmailException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEmailNotValidException(EmailNotValidException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNameFieldsCanNotBeEmptyException(NameFieldsCanNotBeEmptyException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
