package com.jcathcart.bookstore.services;

import com.jcathcart.bookstore.model.Genre;
import com.jcathcart.bookstore.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre getById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public List<Genre> getAll() {
        return (ArrayList) genreRepository.findAll();
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
