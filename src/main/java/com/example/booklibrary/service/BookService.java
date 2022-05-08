package com.example.booklibrary.service;

import com.example.booklibrary.dto.BookDto;
import com.example.booklibrary.exceptions.BookNotFoundException;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private static final String BOOK_NOT_FOUND_MESSAGE_TEMPLATE = "Book with isbn %d not found";

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(Long isbn) throws BookNotFoundException {
        return bookRepository.findById(isbn).orElseThrow(
                () -> new BookNotFoundException(String.format(BOOK_NOT_FOUND_MESSAGE_TEMPLATE, isbn)));
    }

    public Book createBook(BookDto bookDto) {
        return bookRepository.save(BookDto.mapToBook(bookDto));
    }

    public Book updateBook(Long id, BookDto bookDto) throws BookNotFoundException {
        if (!bookExists(id)) {
            throw new BookNotFoundException(String.format(BOOK_NOT_FOUND_MESSAGE_TEMPLATE, id));
        }

        return bookRepository.save(BookDto.mapToBook(bookDto));
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        if (!bookExists(id)) {
            throw new BookNotFoundException(String.format(BOOK_NOT_FOUND_MESSAGE_TEMPLATE, id));
        }

        bookRepository.deleteById(id);
    }


    private boolean bookExists(Long id) {
        return bookRepository.findById(id).isPresent();
    }
}
