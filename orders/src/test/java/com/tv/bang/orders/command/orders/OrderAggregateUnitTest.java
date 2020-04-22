package com.tv.bang.orders.command.orders;

import com.tv.bang.orders.command.order.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        fixture.givenNoPriorActivity()
            .when(new CreateOrderCommand(orderId, itemIds))
            .expectEvents(new OrderCreatedEvent(orderId, itemIds));
    }

    @Test
    public void givenOrderCreatedEventWhenCheckoutOrderCommandThenShouldPublishOrderCheckedOutEvent() {
        String orderId = UUID.randomUUID().toString();
        List<String> itemIds = Arrays.asList(UUID.randomUUID().toString());
        fixture.given(new OrderCreatedEvent(orderId, itemIds))
            .when(new CheckOutOrderCommand(orderId))
            .expectEvents(new OrderCheckedOutEvent(orderId));
    }

    @Test
    public void givenOrderCheckedOutEventWhenCancelOrderCommandThenShouldThrowOrderAlreadyCheckedOutException() {
        String orderId = UUID.randomUUID().toString();
        List<String> itemIds = Arrays.asList(UUID.randomUUID().toString());
        fixture.given(new OrderCreatedEvent(orderId, itemIds), new OrderCheckedOutEvent(orderId))
            .when(new CancelOrderCommand(orderId))
            .expectException(OrderAlreadyCheckedOutException.class);
    }

    @Test
    public void givenOrderCreatedEventWhenCancelOrderCommandThenShouldPublishOrderCanceledEvent() {
        String orderId = UUID.randomUUID().toString();
        List<String> itemIds = Arrays.asList(UUID.randomUUID().toString());
        fixture.given(new OrderCreatedEvent(orderId, itemIds))
            .when(new CancelOrderCommand(orderId))
            .expectEvents(new OrderCanceledEvent(orderId));
    }
}
