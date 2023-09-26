package com.enessimsek.securepay.exception;

public class NameFieldsCanNotBeEmptyException extends RuntimeException {

    public NameFieldsCanNotBeEmptyException() {
        super("Please do not leave the name field empty.");
    }
}
