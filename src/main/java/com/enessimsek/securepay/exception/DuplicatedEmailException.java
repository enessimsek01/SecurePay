package com.enessimsek.securepay.exception;


public class DuplicatedEmailException extends RuntimeException{

    public DuplicatedEmailException(String email) {
        super("This mail address is already exist: " + email);
    }
}
