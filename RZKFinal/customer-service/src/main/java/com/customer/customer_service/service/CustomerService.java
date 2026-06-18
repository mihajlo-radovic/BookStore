package com.customer.customer_service.service;

import com.customer.customer_service.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> fetchAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer getCustomerByEmail(String email);
    Customer createCustomer(Customer newCustomer);
    Customer updateCustomer(Customer newCustomer, Long id);
    String deleteCustomer(Long id);
}
