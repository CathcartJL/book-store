package com.jcathcart.bookstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcathcart.bookstore.constants.BookConstants;
import com.jcathcart.bookstore.model.Book;
import com.jcathcart.bookstore.services.BookService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BookControllerTest {

    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    MockMvc mockMvc;

    Book book1;
    Book book2;
    Book book3;

    List<Book> bookList;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        book1 = new Book();
        book1.setId(1L);
        book1.setName("Billy the Mountain");

        book2 = new Book();
        book2.setId(2L);
        book2.setName("The Humble Abode");

        book3 = new Book();
        book3.setId(3L);
        book3.setName("The Cold Mile");

        bookList = Arrays.asList(book1, book2, book3);
    }

    @Test
    void getAllBooks() throws Exception {

        when(bookService.getAll()).thenReturn(bookList);

        mockMvc.perform(get(BookConstants.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        verify(bookService, times(1)).getAll();
    }

    @Test
    void getBookById() throws Exception {

        when(bookService.getById(anyLong())).thenReturn(book1);

        mockMvc.perform(get(BookConstants.BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(bookService, times(1)).getById(anyLong());
    }

    @Test
    void saveBook() throws Exception {

        when(bookService.save(any(Book.class))).thenReturn(book1);

        mockMvc.perform(
                post(BookConstants.BASE_URL)
                        .content(objectMapper.writeValueAsString(book1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(bookService, times(1)).save(any(Book.class));
    }

    @Test
    void updateBook() throws Exception {

        when(bookService.save(any(Book.class))).thenReturn(book1);

        mockMvc.perform(
                put(BookConstants.BASE_URL)
                        .content(objectMapper.writeValueAsString(book1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(bookService, times(1)).save(any(Book.class));
    }

    @Test
    void deleteBookById() throws Exception {

        mockMvc.perform(delete(BookConstants.BASE_URL + "/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteById(anyLong());
    }
}
