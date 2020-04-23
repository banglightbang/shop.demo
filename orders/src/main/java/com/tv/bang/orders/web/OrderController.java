package com.tv.bang.orders.web;

import com.tv.bang.orders.query.order.Order;
import com.tv.bang.orders.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public Order get(@PathVariable String id) {
        return orderService.findById(id);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.findAll();
    }

    @PostMapping
    public void create(@RequestBody List<String> itemsIds) throws Exception {
        orderService.create(itemsIds);
    }

    @PutMapping("/{id}/checkout")
    public void checkout(@PathVariable String id) {
        orderService.checkout(id);
    }

    @DeleteMapping("/{id}")
    public void create(@PathVariable String id) {
        orderService.cancel(id);
    }
}
