package com.tv.bang.orders.service;

import com.tv.bang.orders.query.order.Order;

import java.util.List;

public interface OrderService {
    Order findById(String orderId);

    List<Order> findAll();

    void create(List<String> itemsId) throws InvalidOrderException;

    void checkout(String id);

    void cancel(String id);
}
