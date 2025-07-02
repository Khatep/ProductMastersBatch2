package org.khatep.repositories;

import org.khatep.domain.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
}