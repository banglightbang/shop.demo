package com.tv.bang.inventory.web;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.tv.bang.inventory.InventoryApplication;
import com.tv.bang.inventory.domain.Item;
import com.tv.bang.inventory.repository.ItemRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

@AutoConfigureMockMvc
@SpringBootTest(classes = InventoryApplication.class)
public class ItemRestTest {

    private static final String ITEMS_ENDPOINT = "/items";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();

        initData();
    }

    @Test
    public void whenGetItem_thenOK() throws Exception {
        Item expected = findItem();

        mockMvc.perform(get(ITEMS_ENDPOINT + "/{id}", expected.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(expected.getName()))
            .andExpect(jsonPath("$.price").value(expected.getPrice()));
    }

    @Test
    public void whenGetAllItems_thenOK() throws Exception {
        Item expected = findItem();

        mockMvc.perform(get(ITEMS_ENDPOINT))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$..items", hasSize(1)))
            .andExpect(jsonPath("$..items[0].name").value(expected.getName()))
            .andExpect(jsonPath("$..items.[0].price").value(expected.getPrice()));
    }

    private void initData() {
        itemRepository.deleteAll();

        Item item = new Item();
        item.setName("TestName");
        item.setPrice(new BigDecimal(1.1));
        itemRepository.save(item);
    }


    private Item findItem() {
        return itemRepository.findAll().get(0);
    }
}
