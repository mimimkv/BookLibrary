package com.example.booklibrary.service;

import com.example.booklibrary.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetAllBooksEmptyListIsReturned() {
        Assertions.assertEquals(Collections.emptyList(), bookService.getAllBooks(),
                "Method should return an empty list when there are no books in the repository");
    }

    @Test
    public void testCreateBook() {

    }
}