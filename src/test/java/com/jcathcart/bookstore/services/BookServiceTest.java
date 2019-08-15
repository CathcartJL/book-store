package com.jcathcart.bookstore.services;

import com.jcathcart.bookstore.model.Book;
import com.jcathcart.bookstore.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    Book book1;
    Book book2;
    Book book3;

    List<Book> bookList;

    @BeforeEach
    void setUp() {

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
    void getBookById() {

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book1));

        Book returnedBook = bookService.getById(1L);

        assertEquals("Billy the Mountain", returnedBook.getName());

        verify(bookRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAllBooks() {

        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> returnedList = bookService.getAll();

        assertEquals(3, returnedList.size());
        assertTrue(returnedList.get(1).getName().equals("The Humble Abode"));

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void save() {

        when(bookRepository.save(any(Book.class))).thenReturn(book1);

        Book savedBook = bookService.save(book1);

        assertEquals("Billy the Mountain", savedBook.getName());

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void deleteById() {

        bookService.deleteById(1L);

        verify(bookRepository, times(1)).deleteById(anyLong());
    }
}
