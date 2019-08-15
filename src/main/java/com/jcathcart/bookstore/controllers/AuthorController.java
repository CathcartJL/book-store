package com.jcathcart.bookstore.controllers;

import com.jcathcart.bookstore.constants.AuthorConstants;
import com.jcathcart.bookstore.model.Author;
import com.jcathcart.bookstore.services.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private final Logger log = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(AuthorConstants.BASE_URL)
    public List<Author> getAllAuthors() {
        return authorService.getAll();
    }

    @GetMapping(AuthorConstants.BASE_URL + "/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @PostMapping(AuthorConstants.BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PutMapping(AuthorConstants.BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public Author updateAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @DeleteMapping(AuthorConstants.BASE_URL + "/{id}")
    public void deleteAuthorById(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}
