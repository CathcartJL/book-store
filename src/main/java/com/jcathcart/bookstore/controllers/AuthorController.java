package com.jcathcart.bookstore.controllers;

import com.jcathcart.bookstore.constants.AuthorConstants;
import com.jcathcart.bookstore.model.Author;
import com.jcathcart.bookstore.services.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return authorService.getAllAuthors();
    }
}
