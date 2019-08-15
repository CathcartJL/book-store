package com.jcathcart.bookstore.controllers;

import com.jcathcart.bookstore.constants.PublisherConstants;
import com.jcathcart.bookstore.model.Publisher;
import com.jcathcart.bookstore.services.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublisherController {

    private final Logger log = LoggerFactory.getLogger(PublisherController.class);
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(PublisherConstants.BASE_URL)
    public List<Publisher> getAllPublishers() {
        return publisherService.getAll();
    }

    @GetMapping(PublisherConstants.BASE_URL + "/{id}")
    public Publisher getPublisherById(@PathVariable Long id) {
        return publisherService.getById(id);
    }

    @PostMapping(PublisherConstants.BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher savePublisher(@RequestBody Publisher publisher) {
        return publisherService.save(publisher);
    }

    @PutMapping(PublisherConstants.BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher updatePublisher(@RequestBody Publisher publisher) {
        return publisherService.save(publisher);
    }

    @DeleteMapping(PublisherConstants.BASE_URL + "/{id}")
    public void deletePublisherById(@PathVariable Long id) {
        publisherService.deleteById(id);
    }
}
