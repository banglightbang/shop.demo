package com.tv.bang.orders.command.order;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class OrderCreatedEvent {
    private String orderId;
    private List<String> itemIds;
    private BigDecimal totalPrice;
}
