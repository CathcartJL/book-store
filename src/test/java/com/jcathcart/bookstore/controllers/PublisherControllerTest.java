package com.jcathcart.bookstore.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jcathcart.bookstore.constants.PublisherConstants;
import com.jcathcart.bookstore.model.Publisher;
import com.jcathcart.bookstore.services.PublisherService;
import com.jcathcart.bookstore.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PublisherControllerTest {

    Publisher publisher1;
    Publisher publisher2;
    Publisher publisher3;

    List<Publisher> publisherList;

    @Mock
    PublisherService publisherService;

    @InjectMocks
    PublisherController publisherController;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {

        publisher1 = new Publisher(1L, "O'Reilly, Inc");
        publisher2 = new Publisher(2L, "Pearson Education, Inc");
        publisher3 = new Publisher(3L, "Manning Publications");

        publisherList = Arrays.asList(publisher1, publisher2, publisher3);

        mockMvc = MockMvcBuilders.standaloneSetup(publisherController).build();
    }

    @Test
    void getAllPublishers() throws Exception {

        when(publisherService.getAll()).thenReturn(publisherList);

        mockMvc.perform(get(PublisherConstants.BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        verify(publisherService, times(1)).getAll();
    }

    @Test
    void getPublisherById() throws Exception {

        when(publisherService.getById(anyLong())).thenReturn(publisher1);

        mockMvc.perform(get(PublisherConstants.BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(publisherService, times(1)).getById(anyLong());
    }

    @Test
    void savePublisher() throws Exception {

        when(publisherService.save(any(Publisher.class))).thenReturn(publisher1);

        mockMvc.perform(
                post(PublisherConstants.BASE_URL)
                        .content(JsonUtil.serialize(publisher1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(publisherService, times(1)).save(any(Publisher.class));
    }

    @Test
    void updatePublisher() throws Exception {

        when(publisherService.save(any(Publisher.class))).thenReturn(publisher1);

        mockMvc.perform(
                put(PublisherConstants.BASE_URL)
                        .content(JsonUtil.serialize(publisher1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(publisherService, times(1)).save(any(Publisher.class));
    }

    @Test
    void deletePublisherById() throws Exception {

        mockMvc.perform(delete(PublisherConstants.BASE_URL + "/1"))
                .andExpect(status().isOk());

        verify(publisherService, times(1)).deleteById(anyLong());
    }
}
