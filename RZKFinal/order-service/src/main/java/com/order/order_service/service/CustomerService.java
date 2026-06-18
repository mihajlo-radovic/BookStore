package com.order.order_service.service;

import com.order.order_service.client.CustomerClient;
import com.order.order_service.exception.CustomerNotFoundException;
import com.order.order_service.model.Customer;
import com.order.order_service.model.CustomerResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerClient customerClient;

    @CircuitBreaker(name = "customerServiceCB", fallbackMethod = "fallbackGetCustomer")
    public CustomerResponse getCustomer(Long id) {
        CustomerResponse customerResponse = new CustomerResponse();
        Customer customer = customerClient.getCustomerById(id);
        if (customer != null) {
            customerResponse.setCustomer(customer);
            customerResponse.setError(false);
        }
        return customerResponse;
    }

    public CustomerResponse fallbackGetCustomer(Long id, Throwable t) {
        CustomerResponse customerResponse = new CustomerResponse();
        if(t instanceof CustomerNotFoundException){
            customerResponse.setError(true);
            customerResponse.setErrorMessage(t.getMessage());
        }else{
            customerResponse.setError(true);
            customerResponse.setErrorMessage("Customer service temporarily unavailable");
        }
        return customerResponse;
    }

}
