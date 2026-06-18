package com.order.order_service.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String email) {
        super("Customer with this email already exists: "+email);
    }
}
