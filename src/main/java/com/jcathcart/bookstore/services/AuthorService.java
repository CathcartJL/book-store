package com.jcathcart.bookstore.services;

import com.jcathcart.bookstore.model.Author;
import com.jcathcart.bookstore.repositories.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final Logger log = LoggerFactory.getLogger(AuthorService.class);
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getById(Long id) {
        log.debug("Getting author with id: {}", id);
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> getAll() {
        log.debug("Getting all authors...");
        return (List) authorRepository.findAll();
    }

    public Author save(Author author) {
        log.debug("Saving author: {}", author);
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        log.debug("Deleting author with id: {}", id);
        authorRepository.deleteById(id);
    }
}
