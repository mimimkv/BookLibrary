package com.example.booklibrary.controller;

import com.example.booklibrary.dto.BookDto;
import com.example.booklibrary.exceptions.BookNotFoundException;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library") //-> localhost:8080/library
public class BookController {
    private static final String BOOK_NOT_FOUND_MESSAGE_TEMPLATE = "Book with isbn %d not found";

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());

        return bookRepository.save(book);
    }

    @DeleteMapping("/books/{isbn}")
    public void delete(@PathVariable Long isbn) throws BookNotFoundException {
        if (!bookRepository.existsById(isbn)) {
            throw new BookNotFoundException(String.format(BOOK_NOT_FOUND_MESSAGE_TEMPLATE, isbn));
        }

        bookRepository.deleteById(isbn);
    }
}
