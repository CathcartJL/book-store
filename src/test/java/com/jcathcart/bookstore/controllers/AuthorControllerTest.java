package com.jcathcart.bookstore.controllers;

import com.jcathcart.bookstore.constants.AuthorConstants;
import com.jcathcart.bookstore.model.Author;
import com.jcathcart.bookstore.services.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AuthorControllerTest {

    @Mock
    AuthorService authorService;

    @InjectMocks
    AuthorController authorController;

    MockMvc mockMvc;

    Author author1;
    Author author2;
    Author author3;

    List<Author> authorList;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();

        author1 = new Author(1L, "Terry", "Pratchett");
        author2 = new Author(2L, "Tamora", "Pierce");
        author3 = new Author(3L, "E.B.", "White");

        authorList = Arrays.asList(author1, author2, author3);
    }

    @Test
    void getAllAuthors() throws Exception {

        when(authorService.getAllAuthors()).thenReturn(authorList);

        mockMvc.perform(get(AuthorConstants.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        verify(authorService, times(1)).getAllAuthors();
    }

    @Test
    void getAuthorbyId() throws Exception {

        when(authorService.getAuthorbyId(anyLong())).thenReturn(author1);

        mockMvc.perform(get(AuthorConstants.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(authorService, times(1)).getAuthorbyId(anyLong());
    }
}