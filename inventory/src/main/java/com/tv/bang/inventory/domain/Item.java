package com.tv.bang.inventory.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Item {

    @Id
    private String id;
    private String name;
    private BigDecimal price;
}
