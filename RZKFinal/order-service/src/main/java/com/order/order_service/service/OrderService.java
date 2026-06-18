package com.order.order_service.service;

import com.order.order_service.entity.Orders;

import java.util.List;

public interface OrderService {

    List<Orders> getAllOrders();
    Orders createOrder(Orders newOrder);
}
