package com.tv.bang.orders.query.order;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Order {

    public enum OrderStatus {
        CREATED,
        CHECKED_OUT,
        CANCELED
    }

    @Id
    private String id;
    private List<String> items;
    private OrderStatus orderStatus;
}

