package com.jcathcart.bookstore.repositories;

import com.jcathcart.bookstore.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends CrudRepository<Genre, Long> {
}
