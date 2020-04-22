package com.tv.bang.orders.web;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private List<String> itemIds;
}
