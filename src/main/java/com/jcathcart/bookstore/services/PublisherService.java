package com.jcathcart.bookstore.services;

import com.jcathcart.bookstore.model.Publisher;
import com.jcathcart.bookstore.repositories.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final Logger log = LoggerFactory.getLogger(PublisherService.class);
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher getById(Long id) {
        log.debug("Getting publisher with id: {}", id);
        return publisherRepository.findById(id).orElse(null);
    }

    public List<Publisher> getAll() {
        log.debug("Getting all publishers.");
        return (List) publisherRepository.findAll();
    }

    public Publisher save(Publisher publisher) {
        log.debug("Saving publisher: {}", publisher);
        return publisherRepository.save(publisher);
    }

    public void deleteById(Long id) {
        log.debug("Deleting publisher with id: {}", id);
        publisherRepository.deleteById(id);
    }

}
