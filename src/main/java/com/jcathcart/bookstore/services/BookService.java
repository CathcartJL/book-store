package com.jcathcart.bookstore.services;

import com.jcathcart.bookstore.model.Book;
import com.jcathcart.bookstore.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final Logger log = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getById(Long id) {
        log.debug("Getting book with id: {}", id);
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getAll() {
        log.debug("Getting all books...");
        return (List) bookRepository.findAll();
    }

    public Book save(Book book) {
        log.debug("Saving book: {}", book);
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        log.debug("Deleting author with id: {}", id);
        bookRepository.deleteById(id);
    }

}
