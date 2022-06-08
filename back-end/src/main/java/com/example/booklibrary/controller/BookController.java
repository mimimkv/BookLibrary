package com.example.booklibrary.controller;

import com.example.booklibrary.dto.BookDto;
import com.example.booklibrary.dto.PlainBookDto;
import com.example.booklibrary.exceptions.BookNotFoundException;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library") //-> localhost:8080/library
@CrossOrigin
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<PlainBookDto>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<PlainBookDto> booksDtoList = books.stream().map(PlainBookDto::from).collect(Collectors.toList());

        return new ResponseEntity<>(booksDtoList, HttpStatus.OK);
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable Long isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @PostMapping("/books")
    public Book addBook(@Valid @RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDto) {
        Book  book = bookService.updateBook(id, bookDto);
        return new ResponseEntity<>(BookDto.from(book), HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long isbn) {
        /*try {
            bookService.deleteBook(isbn);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/

        bookService.deleteBook(isbn);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
