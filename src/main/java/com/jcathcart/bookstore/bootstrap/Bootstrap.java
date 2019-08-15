package com.jcathcart.bookstore.bootstrap;

import com.jcathcart.bookstore.model.Author;
import com.jcathcart.bookstore.model.Book;
import com.jcathcart.bookstore.model.Genre;
import com.jcathcart.bookstore.model.Publisher;
import com.jcathcart.bookstore.services.AuthorService;
import com.jcathcart.bookstore.services.BookService;
import com.jcathcart.bookstore.services.GenreService;
import com.jcathcart.bookstore.services.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class Bootstrap implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;

    @Autowired
    public Bootstrap(BookService bookService, AuthorService authorService, GenreService genreService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.publisherService = publisherService;
        log.debug("I am Bootstrap");
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.debug("Bootstrap is on the run!");
        initData();
    }

    private void initData() {
        Author author1 = new Author();
        Genre genre1 = new Genre();
        Publisher publisher1 = new Publisher();
        Book book1 = new Book();

        author1.setFirstName("John");
        author1.setLastName("Austin");
        Author savedAuthor1 = authorService.save(author1);

        publisher1.setName("Schill Factor");
        Publisher savedPublisher1 = publisherService.save(publisher1);

        genre1.setName("Horror");
        Genre savedGrenre = genreService.save(genre1);

        book1.setName("Tusk");
        book1.setAuthor(savedAuthor1);
        book1.setPublisher(savedPublisher1);
        book1.setGenre(savedGrenre);
        bookService.save(book1);
    }
}
