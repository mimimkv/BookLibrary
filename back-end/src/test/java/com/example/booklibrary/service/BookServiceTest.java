package com.example.booklibrary.service;

import com.example.booklibrary.dto.BookDto;
import com.example.booklibrary.exceptions.BookNotFoundException;
import com.example.booklibrary.model.Author;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.enums.Category;
import com.example.booklibrary.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    private BookService bookService;
    @Mock
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository, authorService);
        Mockito.lenient().
            when(authorService.findByName("firstName", "lastName")).thenReturn(new Author("firstName", "lastName"));
    }

    @Test
    public void testGetAllBooks() {
        bookService.getAllBooks();
        verify(bookRepository).findAll();
    }

    @Test
    public void testCreateOneBook() {
        String title = "book1";
        Author author = new Author("firstName", "lastName");

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(Category.FANTASY);

        BookDto bookDto = BookDto.from(book);

        bookService.createBook(bookDto);

        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        Book captorValue = bookArgumentCaptor.getValue();
        assertThat(captorValue).isEqualTo(book);
    }

    @Test
    public void testGetAllBooksEmptyListIsReturned() {
        Assertions.assertEquals(Collections.emptyList(), bookService.getAllBooks(),
            "Method should return an empty list when there are no books in the repository");
    }

    @Test
    public void test() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("title");
        bookDto.setAuthor(new Author("firstName", "lastName"));

        bookService.createBook(bookDto);
        verify(bookRepository).save(BookDto.mapToBook(bookDto));
    }


    @Test
    public void testGetBookByIsbnThrowsExceptionNoBooksInRepo() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.getBookByIsbn(1L),
            "BookNotFoundException expected when there are no books in the repository");
    }

    @Test
    public void testCreateBook() {
        String title = "Travel book";
        BookDto bookDto = new BookDto();
        bookDto.setTitle(title);
        bookDto.setAuthor(new Author("firstName", "lastName"));

        when(bookRepository.save(any())).thenReturn(BookDto.mapToBook(bookDto));

        assertEquals(bookDto.getTitle(), bookService.createBook(bookDto).getTitle(),
            "CreateBook method in BookService does not create and does not return the correct book");
    }

    @Test
    public void testUpdateBookThrowsException() {
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(1L, null),
            "BookNotFoundException expected when there is no such book in the repository");
    }

    @Test
    public void testDeleteBookThrowsExceptionNoSuchBook() {
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(1L),
            "BookNotFoundException expected when there is no such book in the repository");
    }
}