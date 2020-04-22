package com.tv.bang.orders.command.order;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.List;

@Value
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    private String orderId;
    private List<String> itemIds;
    private BigDecimal totalPrice;
}
