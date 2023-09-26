package com.enessimsek.securepay.exception;

public class NameSizesIsNotAvailableException extends RuntimeException {

    public NameSizesIsNotAvailableException() {
        super("Name sizes should be between 2 and 50");
    }
}
