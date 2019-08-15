package com.jcathcart.bookstore.controllers;

import com.jcathcart.bookstore.constants.AppConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AppControllerTest {

    MockMvc mockMvc;

    @Autowired
    AppController appController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
    }

    @Test
    void healthCheck() throws Exception {
        mockMvc.perform(get(AppConstants.HEALTH_CHECK_URL).contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String response = mvcResult.getResponse().getContentAsString();
                    assertEquals(AppConstants.HEALTH_CHECK_MESSAGE, response);
                });
    }

    @Test
    void memoryCheck() throws Exception {
        mockMvc.perform(get(AppConstants.MEMORY_USAGE_URL).contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String response = mvcResult.getResponse().getContentAsString();
                    assertTrue(response.contains("Free memory"));
                });
    }
}
