package com.tv.bang.orders.web;

import com.tv.bang.orders.command.order.CreateOrderCommand;
import com.tv.bang.orders.query.order.Order;
import com.tv.bang.orders.query.order.OrderRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderController {

    private final CommandGateway commandGateway;
    private final OrderRepository orderRepository;

    public OrderController(CommandGateway commandGateway, OrderRepository orderRepository) {
        this.commandGateway = commandGateway;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders/{id}")
    public Order get(@PathVariable String id) {
        return orderRepository.findById(id).get();
    }

    @PostMapping("/orders")
    public void create(OrderDto order) {
        String orderId = UUID.randomUUID().toString();
        CreateOrderCommand command = new CreateOrderCommand(orderId,
                order.getItemIds());

        commandGateway.send(command);
    }
}
