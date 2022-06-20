package com.example.booklibrary.service;

import com.example.booklibrary.dto.BookDto;
import com.example.booklibrary.exceptions.BookNotFoundException;
import com.example.booklibrary.model.Author;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private static final String BOOK_NOT_FOUND_MESSAGE_TEMPLATE = "Book with isbn %d not found";

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(Long isbn) {
        return bookRepository.findById(isbn).orElseThrow(
            () -> new BookNotFoundException(String.format(BOOK_NOT_FOUND_MESSAGE_TEMPLATE, isbn)));
    }

    public Book createBook(BookDto bookDto) {
        Author author =
            authorService.findByName(bookDto.getAuthor().getFirstName(), bookDto.getAuthor().getLastName());
        Book book = BookDto.mapToBook(bookDto);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDto bookDto) {
        if (!bookExists(id)) {
            throw new BookNotFoundException(String.format(BOOK_NOT_FOUND_MESSAGE_TEMPLATE, id));
        }

        Author author = authorService.findByName(bookDto.getAuthor().getFirstName(), bookDto.getAuthor().getLastName());
        Book book = BookDto.mapToBook(bookDto);
        book.setIsbn(id);
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookExists(id)) {
            throw new BookNotFoundException(String.format(BOOK_NOT_FOUND_MESSAGE_TEMPLATE, id));
        }

        bookRepository.deleteById(id);
    }


    private boolean bookExists(Long id) {
        return bookRepository.findById(id).isPresent();
    }
}
