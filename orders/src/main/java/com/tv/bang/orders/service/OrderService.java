package com.tv.bang.orders.service;

import com.tv.bang.orders.query.order.Order;

import java.util.List;

public interface OrderService {
    Order findById(String orderId);
    void create(List<String> itemsId) throws InvalidOrderException;
}
