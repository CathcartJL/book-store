package com.jcathcart.bookstore.services;

import com.jcathcart.bookstore.model.Genre;
import com.jcathcart.bookstore.repositories.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final Logger log = LoggerFactory.getLogger(GenreService.class);
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre getById(Long id) {
        log.debug("Getting genre with id: {}", id);
        return genreRepository.findById(id).orElse(null);
    }

    public List<Genre> getAll() {
        log.debug("Getting all genres...");
        return (List) genreRepository.findAll();
    }

    public Genre save(Genre genre) {
        log.debug("Saving genre: {}", genre);
        return genreRepository.save(genre);
    }

    public void deleteById(Long id) {
        log.debug("Delete genre with id: {}", id);
        genreRepository.deleteById(id);
    }
}
