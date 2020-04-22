package com.tv.bang.orders.client;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private String id;
    private String name;
    private BigDecimal price;
}
