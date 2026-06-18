package com.order.order_service.controller;

import com.order.order_service.entity.Orders;
import com.order.order_service.model.CustomerResponse;
import com.order.order_service.service.CustomerService;
import com.order.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeorder")
    public ResponseEntity<String> postMethod(@RequestBody Orders newOrder){
        CustomerResponse customerResponse = customerService.getCustomer(newOrder.getCustomerId());
        if((customerResponse.getErrorMessage()!=null) && customerResponse.getErrorMessage().contains("Customer not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerResponse.getErrorMessage());

        }
        else if((customerResponse.getErrorMessage()!=null) && customerResponse.getErrorMessage().contains("Customer service temporarily unavailable.")){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Customer service temporarily unavailable. Please try again later.");
        }
        return ResponseEntity.ok("Order placed successfully: "+orderService.createOrder(newOrder));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
