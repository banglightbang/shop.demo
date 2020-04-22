package com.tv.bang.orders.command.order;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CheckOutOrderCommand {
    @TargetAggregateIdentifier
    private String orderId;
}
