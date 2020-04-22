package com.tv.bang.orders.service;

import com.tv.bang.orders.client.Item;
import com.tv.bang.orders.client.ItemClient;
import com.tv.bang.orders.command.order.CreateOrderCommand;
import com.tv.bang.orders.query.order.FindOrderQuery;
import com.tv.bang.orders.query.order.Order;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    private final ItemClient itemClient;

    @Override
    public Order findById(String orderId) {
        return queryGateway.query(new FindOrderQuery(orderId), Order.class).join();
    }

    @Override
    public void create(List<String> itemIds) throws InvalidOrderException {
        String orderId = UUID.randomUUID().toString();
        CreateOrderCommand command = new CreateOrderCommand(orderId, itemIds, calculatePrice(itemIds));

        commandGateway.send(command);
    }

    private BigDecimal calculatePrice(List<String> itemIds) throws InvalidOrderException {
        Collection<Item> items = itemClient.findByIdIn(itemIds).getContent();
        boolean itemsExist = items.stream()
                .map(Item::getId)
                .allMatch(itemIds::contains);

        if (items.isEmpty() || !itemsExist) {
            throw new InvalidOrderException("Order items are not valid");
        }
        return items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
