package com.tv.bang.orders.command.order;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private List<String> itemIds;
    private OrderStatus status;
    private BigDecimal totalPrice;

    protected OrderAggregate() {}

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        apply(new OrderCreatedEvent(command.getOrderId(), command.getItemIds(), command.getTotalPrice()));
    }

    @CommandHandler
    public void handle(CheckOutOrderCommand command) {
        apply(new OrderCheckedOutEvent(command.getOrderId()));
    }

    @CommandHandler
    public void handle(CancelOrderCommand command) throws OrderAlreadyCheckedOutException {
        if (OrderStatus.CHECKED_OUT.equals(status)) {
            throw new OrderAlreadyCheckedOutException("Cannot cancel already checked out order.");
        }
        apply(new OrderCanceledEvent(command.getOrderId()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        this.itemIds = event.getItemIds();
        this.status = OrderStatus.CREATED;
        this.totalPrice = event.getTotalPrice();
    }

    @EventSourcingHandler
    public void on(OrderCheckedOutEvent event) {
        this.status = OrderStatus.CHECKED_OUT;
    }

    @EventSourcingHandler
    public void on(OrderCanceledEvent event) {
        this.status = OrderStatus.CANCELED;
    }

    public enum OrderStatus {
        CREATED,
        CHECKED_OUT,
        CANCELED
    }
}
