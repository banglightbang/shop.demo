package com.tv.bang.orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("inventory")
public interface ItemClient {

    @GetMapping("/items/search/findByIdIn")
    CollectionModel<Item> findByIdIn(@RequestParam("ids") List<String> ids);
}
