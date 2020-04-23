package com.tv.bang.api.gateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@AutoConfigureMockMvc
@SpringBootTest
public class RoutingTests {

    private final static String INVENTORY_PATH = "/inventory";
    private final static String ORDERS_PATH = "/ordering";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void whenSendRequestToInventoryThenOK() throws Exception {
        mockMvc.perform(get(INVENTORY_PATH + "/items"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenSendRequestToOrdersThenOK() throws Exception {
        mockMvc.perform(get(ORDERS_PATH + "/orders"))
                .andExpect(status().isOk());
    }
}
