package com.tv.bang.orders.command.order;

import lombok.Value;

import java.util.List;

@Value
public class OrderCreatedEvent {
    private String orderId;
    private List<String> itemIds;
}
