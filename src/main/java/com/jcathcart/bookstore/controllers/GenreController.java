package com.jcathcart.bookstore.controllers;

import com.jcathcart.bookstore.constants.GenreConstants;
import com.jcathcart.bookstore.model.Genre;
import com.jcathcart.bookstore.services.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {

    private final Logger log = LoggerFactory.getLogger(GenreController.class);
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(GenreConstants.BASE_URL)
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }

    @GetMapping(GenreConstants.BASE_URL + "/{id}")
    public Genre getGenreById(@PathVariable Long id) {
        return genreService.getById(id);
    }

    @PostMapping(GenreConstants.BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public Genre saveGenre(@RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @PutMapping(GenreConstants.BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public Genre updateGenre(@RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @DeleteMapping(GenreConstants.BASE_URL + "/{id}")
    public void deleteGenreById(@PathVariable Long id) {
        genreService.deleteById(id);
    }
}
