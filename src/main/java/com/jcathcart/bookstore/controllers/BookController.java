package com.jcathcart.bookstore.controllers;

import com.jcathcart.bookstore.constants.BookConstants;
import com.jcathcart.bookstore.model.Book;
import com.jcathcart.bookstore.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(BookConstants.BASE_URL)
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping(BookConstants.BASE_URL + "/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping(BookConstants.BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping(BookConstants.BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping(BookConstants.BASE_URL + "/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
