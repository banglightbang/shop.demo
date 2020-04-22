package com.tv.bang.orders.command.order;

import lombok.Value;

@Value
public class OrderCheckedOutEvent {
    private String orderId;
}
