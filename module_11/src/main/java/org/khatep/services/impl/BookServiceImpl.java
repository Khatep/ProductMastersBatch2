package org.khatep.services.impl;

import lombok.RequiredArgsConstructor;
import org.khatep.domain.Book;
import org.khatep.repositories.BookRepository;
import org.khatep.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> listBooks() {
        return bookRepository.getAllBooks();
    }
}
