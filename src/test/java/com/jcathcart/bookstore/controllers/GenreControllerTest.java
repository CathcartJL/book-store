package com.jcathcart.bookstore.controllers;

import com.jcathcart.bookstore.constants.GenreConstants;
import com.jcathcart.bookstore.model.Genre;
import com.jcathcart.bookstore.services.GenreService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GenreControllerTest {

    MockMvc mockMvc;

    @Mock
    GenreService genreService;

    @InjectMocks
    GenreController genreController;

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

        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
    }

    @Test
    void getAllGenres() throws Exception {

        when(genreService.getAll()).thenReturn(genreList);

        mockMvc.perform(get(GenreConstants.BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        verify(genreService, times(1)).getAll();
    }

    @Test
    void getGenreById() throws Exception {

        when(genreService.getById(anyLong())).thenReturn(genre1);

        mockMvc.perform(get(GenreConstants.BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(genreService, times(1)).getById(anyLong());
    }

    @Test
    void saveGenre() throws Exception {

        when(genreService.save(any(Genre.class))).thenReturn(genre1);

        mockMvc.perform(
                post(GenreConstants.BASE_URL)
                        .content(JsonUtil.serialize(genre1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(genreService, times(1)).save(any(Genre.class));
    }

    @Test
    void updateGenre() throws Exception {
        when(genreService.save(any(Genre.class))).thenReturn(genre1);

        mockMvc.perform(
                put(GenreConstants.BASE_URL)
                        .content(JsonUtil.serialize(genre1))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        verify(genreService, times(1)).save(any(Genre.class));
    }

    @Test
    void deleteGenreById() throws Exception {

        mockMvc.perform(delete(GenreConstants.BASE_URL + "/1"))
                .andExpect(status().isOk());

        verify(genreService, times(1)).deleteById(anyLong());
    }
}
