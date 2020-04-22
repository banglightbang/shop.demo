package com.tv.bang.orders.query.order;

import com.tv.bang.orders.command.order.OrderCanceledEvent;
import com.tv.bang.orders.command.order.OrderCheckedOutEvent;
import com.tv.bang.orders.command.order.OrderCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderEventListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        Order order = new Order();
        order.setId(event.getOrderId());
        order.setItems(event.getItemIds());
        order.setOrderStatus(Order.OrderStatus.CREATED);
        order.setTotalPrice(event.getTotalPrice());
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCheckedOutEvent event) {
        updateStatus(event.getOrderId(), Order.OrderStatus.CHECKED_OUT);
    }

    @EventHandler
    public void on(OrderCanceledEvent event) {
        updateStatus(event.getOrderId(), Order.OrderStatus.CANCELED);
    }

    @QueryHandler
    public Order handle(FindOrderQuery query) {
        return orderRepository.findById(query.getId()).get();
    }

    private void updateStatus(String orderId, Order.OrderStatus orderStatus) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setOrderStatus(orderStatus);
            orderRepository.save(order);
        });
    }
}
