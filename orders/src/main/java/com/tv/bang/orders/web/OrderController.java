package com.tv.bang.orders.web;

import com.tv.bang.orders.query.order.Order;
import com.tv.bang.orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders/{id}")
    public Order get(@PathVariable String id) {
        return orderService.findById(id);
    }

    @PostMapping("/orders")
    public void create(@RequestBody List<String> itemsIds) throws Exception {
        orderService.create(itemsIds);
    }
}
