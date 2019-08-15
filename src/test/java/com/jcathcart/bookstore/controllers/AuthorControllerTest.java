package com.jcathcart.bookstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcathcart.bookstore.constants.AuthorConstants;
import com.jcathcart.bookstore.model.Author;
import com.jcathcart.bookstore.services.AuthorService;
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

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AuthorControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    AuthorService authorService;

    @InjectMocks
    AuthorController authorController;

    Author author1;
    Author author2;
    Author author3;

    List<Author> authorList;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();

        author1 = new Author(1L, "Robert", "Martin");
        author2 = new Author(2L, "Jennifer", "Wertlos");
        author3 = new Author(3L, "Jessica", "Alba");

        authorList = Arrays.asList(author1, author2, author3);

    }

    @Test
    void getAllAuthors() throws Exception {

        when(authorService.getAll()).thenReturn(authorList);

        mockMvc.perform(get(AuthorConstants.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        verify(authorService, times(1)).getAll();
    }

    @Test
    void getAuthorById() throws Exception {

        when(authorService.getById(anyLong())).thenReturn(author1);

        mockMvc.perform(get(AuthorConstants.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Robert")));

        verify(authorService, times(1)).getById(anyLong());
    }

    @Test
    void saveAuthor() throws Exception {

        when(authorService.save(any(Author.class))).thenReturn(author1);

        mockMvc.perform(
                post(AuthorConstants.BASE_URL)
                        .content(objectMapper.writeValueAsString(author1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(authorService, times(1)).save(any(Author.class));
    }

    @Test
    void updateAuthor() throws Exception {
        when(authorService.save(any(Author.class))).thenReturn(author1);

        mockMvc.perform(
                put(AuthorConstants.BASE_URL)
                        .content(objectMapper.writeValueAsString(author1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(authorService, times(1)).save(any(Author.class));
    }

    @Test
    void deleteAuthorById() throws Exception {

        mockMvc.perform(delete(AuthorConstants.BASE_URL + "/1"))
                .andExpect(status().isOk());

        verify(authorService, times(1)).deleteById(anyLong());
    }
}
