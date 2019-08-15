package com.jcathcart.bookstore.services;

import com.jcathcart.bookstore.model.Publisher;
import com.jcathcart.bookstore.repositories.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PublisherServiceTest {
    @Mock
    PublisherRepository publisherRepository;

    @InjectMocks
    PublisherService publisherService;

    Publisher publisher1;
    Publisher publisher2;
    Publisher publisher3;

    List<Publisher> publisherList;

    @BeforeEach
    void setUp() {

        publisher1 = new Publisher(1L, "O'Reilly, Inc");
        publisher2 = new Publisher(2L, "Pearson Education, Inc");
        publisher3 = new Publisher(3L, "Manning Publications");

        publisherList = Arrays.asList(publisher1, publisher2, publisher3);
    }

    @Test
    void getPublisherById() {

        when(publisherRepository.findById(anyLong())).thenReturn(Optional.of(publisher1));

        Publisher returnedPublisher = publisherService.getById(1L);

        assertEquals("O'Reilly, Inc", returnedPublisher.getName());

        verify(publisherRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAllPublishers() {

        when(publisherRepository.findAll()).thenReturn(publisherList);

        List<Publisher> returnedList = publisherService.getAll();

        assertEquals(3, returnedList.size());
        assertEquals("Pearson Education, Inc", returnedList.get(1).getName());

        verify(publisherRepository, times(1)).findAll();
    }

    @Test
    void save() {

        when(publisherRepository.save(any(Publisher.class))).thenReturn(publisher1);

        Publisher savedPublisher = publisherService.save(publisher1);

        assertEquals("O'Reilly, Inc", savedPublisher.getName());

        verify(publisherRepository, times(1)).save(any(Publisher.class));
    }

    @Test
    void deleteById() {

        publisherService.deleteById(1L);

        verify(publisherRepository, times(1)).deleteById(anyLong());
    }
}
