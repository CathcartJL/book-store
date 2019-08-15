package com.jcathcart.bookstore.services;

import com.jcathcart.bookstore.model.Genre;
import com.jcathcart.bookstore.repositories.GenreRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    GenreService genreService;

    Genre genre1;
    Genre genre2;
    Genre genre3;

    List<Genre> genreList;

    @BeforeEach
    void setUp() {

        genre1 = new Genre(1L, "Comedy");
        genre2 = new Genre(2L, "Drama");
        genre3 = new Genre(3L, "Horror");

        genreList = Arrays.asList(genre1, genre2, genre3);
    }

    @Test
    void getGenreById() {

        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genre1));

        Genre returnedGenre = genreService.getById(1L);

        assertEquals("Comedy", returnedGenre.getName());

        verify(genreRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAllGenres() {

        when(genreRepository.findAll()).thenReturn(genreList);

        List<Genre> returnedList = genreService.getAll();

        assertEquals(3, returnedList.size());
        assertEquals("Drama", returnedList.get(1).getName());

        verify(genreRepository, times(1)).findAll();
    }

    @Test
    void save() {

        when(genreRepository.save(any(Genre.class))).thenReturn(genre1);

        Genre savedGenre = genreService.save(genre1);

        assertEquals("Comedy", savedGenre.getName());

        verify(genreRepository, times(1)).save(any(Genre.class));
    }

    @Test
    void deleteById() {

        genreService.deleteById(1L);

        verify(genreRepository, times(1)).deleteById(anyLong());
    }
}
