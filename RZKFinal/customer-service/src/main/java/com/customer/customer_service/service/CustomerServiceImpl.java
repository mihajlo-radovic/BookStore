package com.customer.customer_service.service;

import com.customer.customer_service.entity.Customer;
import com.customer.customer_service.exception.CustomerAlreadyExistsException;
import com.customer.customer_service.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customer.customer_service.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        Optional<Customer> optCust = customerRepository.findById(id);
        if(optCust.isEmpty()) throw new CustomerNotFoundException(id);
        return optCust;
    }

    @Override
    public Customer createCustomer(Customer newCustomer) {
        Customer customer = customerRepository.getCustomerByEmail(newCustomer.getEmail());
        if(customer != null) throw new CustomerAlreadyExistsException(newCustomer.getEmail());
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(Customer newCustomer, Long id) {
        Optional<Customer> custOpt = customerRepository.findById(id);
        Customer custObj = null;
        if(custOpt.isPresent()) {
            custObj = custOpt.get();
            custObj.setName(newCustomer.getName());
            custObj.setEmail(newCustomer.getEmail());
            custObj.setBookStore(newCustomer.getBookStore());
        }
        return customerRepository.save(custObj);
    }

    @Override
    public String deleteCustomer(Long id) {
        Optional<Customer> custOpt = customerRepository.findById(id);
        Customer custObj = null;
        String deleteMsg = null;
        if(custOpt.isPresent()) {
            custObj = custOpt.get();
            customerRepository.delete(custObj);
            deleteMsg = "Customer with id " + id + " successfully deleted";
        }
        return deleteMsg;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }
}
