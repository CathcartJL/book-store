package com.jcathcart.bookstore.repositories;

import com.jcathcart.bookstore.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
