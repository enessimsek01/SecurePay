package com.enessimsek.securepay.exception;


public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super("Customer is not found");
    }
}
