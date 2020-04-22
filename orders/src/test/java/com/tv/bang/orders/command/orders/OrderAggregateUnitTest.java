package com.tv.bang.orders.command.orders;

import com.tv.bang.orders.command.order.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class OrderAggregateUnitTest {

    private FixtureConfiguration<OrderAggregate> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @Test
    public void giveNoPriorActivityWhenCreateOrderCommandThenShouldPublishOrderCreatedEvent() {
        String orderId = UUID.randomUUID().toString();
        List<String> itemIds = Arrays.asList(UUID.randomUUID().toString());
        BigDecimal totalPrice = new BigDecimal(10);
        fixture.givenNoPriorActivity()
            .when(new CreateOrderCommand(orderId, itemIds, new BigDecimal(10)))
            .expectEvents(new OrderCreatedEvent(orderId, itemIds, totalPrice));
    }

    @Test
    public void givenOrderCreatedEventWhenCheckoutOrderCommandThenShouldPublishOrderCheckedOutEvent() {
        String orderId = UUID.randomUUID().toString();
        List<String> itemIds = Arrays.asList(UUID.randomUUID().toString());
        BigDecimal totalPrice = new BigDecimal(10);
        fixture.given(new OrderCreatedEvent(orderId, itemIds, totalPrice))
            .when(new CheckOutOrderCommand(orderId))
            .expectEvents(new OrderCheckedOutEvent(orderId));
    }

    @Test
    public void givenOrderCheckedOutEventWhenCancelOrderCommandThenShouldThrowOrderAlreadyCheckedOutException() {
        String orderId = UUID.randomUUID().toString();
        List<String> itemIds = Arrays.asList(UUID.randomUUID().toString());
        BigDecimal totalPrice = new BigDecimal(10);
        fixture.given(new OrderCreatedEvent(orderId, itemIds, totalPrice), new OrderCheckedOutEvent(orderId))
            .when(new CancelOrderCommand(orderId))
            .expectException(OrderAlreadyCheckedOutException.class);
    }

    @Test
    public void givenOrderCreatedEventWhenCancelOrderCommandThenShouldPublishOrderCanceledEvent() {
        String orderId = UUID.randomUUID().toString();
        List<String> itemIds = Arrays.asList(UUID.randomUUID().toString());
        BigDecimal totalPrice = new BigDecimal(10);
        fixture.given(new OrderCreatedEvent(orderId, itemIds, totalPrice))
            .when(new CancelOrderCommand(orderId))
            .expectEvents(new OrderCanceledEvent(orderId));
    }
}
