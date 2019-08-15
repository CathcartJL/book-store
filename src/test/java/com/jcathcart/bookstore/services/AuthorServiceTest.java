package com.jcathcart.bookstore.services;

import com.jcathcart.bookstore.model.Author;
import com.jcathcart.bookstore.repositories.AuthorRepository;
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
class AuthorServiceTest {

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorService authorService;

    Author author1;
    Author author2;
    Author author3;

    List<Author> authorList;

    @BeforeEach
    void setUp() {

        author1 = new Author(1L, "Robert", "Martin");
        author2 = new Author(2L, "Jennifer", "Wertlos");
        author3 = new Author(3L, "Jessica", "Alba");

        authorList = Arrays.asList(author1, author2, author3);
    }

    @Test
    void getAuthorById() {

        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author1));

        Author returnedAuthor = authorService.getById(1L);

        assertEquals("Robert", returnedAuthor.getFirstName());

        verify(authorRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAllAuthors() {

        when(authorRepository.findAll()).thenReturn(authorList);

        List<Author> returnedList = authorService.getAll();

        assertEquals(3, returnedList.size());
        assertTrue(returnedList.get(1).getFirstName().equals("Jennifer"));

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void save() {

        when(authorRepository.save(any(Author.class))).thenReturn(author1);

        Author savedAuthor = authorService.save(author1);

        assertEquals("Robert", savedAuthor.getFirstName());

        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void deleteById() {

        authorService.deleteById(1L);

        verify(authorRepository, times(1)).deleteById(anyLong());
    }
}
