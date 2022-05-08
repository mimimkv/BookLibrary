package com.example.booklibrary.controller;

import com.example.booklibrary.dto.BookDto;
import com.example.booklibrary.exceptions.BookNotFoundException;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/library") //-> localhost:8080/library
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable Long isbn) {
        Book book;
        try {
            book = bookService.getBookByIsbn(isbn);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(book);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Book book;
        try {
            book = bookService.updateBook(id, bookDto);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long isbn) {
        try {
            bookService.deleteBook(isbn);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
