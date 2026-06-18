package com.customer.customer_service.exception;

public class CustomerNotFoundException extends RuntimeException {
    private final Long customerId;


    public CustomerNotFoundException(Long id) {
        super("Customer not found with id " + id);
        this.customerId = id;
    }

    public CustomerNotFoundException(String msg, Long id) {
        super(msg);
        this.customerId = id;
    }

    public CustomerNotFoundException(String msg) {
        super(msg);
        this.customerId = null;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
