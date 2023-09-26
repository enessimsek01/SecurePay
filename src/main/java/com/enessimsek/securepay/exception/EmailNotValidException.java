package com.enessimsek.securepay.exception;

public class EmailNotValidException extends RuntimeException{

    public EmailNotValidException(String email) {
        super("Please enter a valid mail address: " + email);
    }
}
