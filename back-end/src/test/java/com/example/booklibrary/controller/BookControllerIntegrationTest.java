package com.example.booklibrary.controller;

import com.example.booklibrary.dto.BookDto;
import com.example.booklibrary.model.Author;
import com.example.booklibrary.model.enums.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(locations = {"classpath:application-test.properties"})
class BookControllerIntegrationTest {
    private static final String GET_BOOKS_REQUEST = "http://localhost:8080/library/books";
    private static final String GET_BOOK_BY_ISBN_REQUEST = "http://localhost:8080/library/books/100000";
    private static final String POST_BOOK_REQUEST = "http://localhost:8080/library/books";

    @Test
    public void testGetBooksNoBooksInDatabase() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(GET_BOOKS_REQUEST, String.class);

        assertEquals("[]", response.getBody());
    }

    @Test
    public void testGetBookByIsbnNoSuchBookInDatabase() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(GET_BOOK_BY_ISBN_REQUEST, String.class);

        HttpStatus expected = HttpStatus.NOT_FOUND;
        assertEquals(expected, response.getStatusCode());
    }

    @Test
    public void testAddBookAuthorNotPresentInDatabase() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Smith");
        author.setId(100000l);

        BookDto bookDto = new BookDto();
        bookDto.setTitle("book1");
        bookDto.setCategory(Category.FANTASY);
        bookDto.setAuthor(author);

        ResponseEntity<String> response = restTemplate.postForEntity(POST_BOOK_REQUEST, bookDto, String.class);

        HttpStatus expectedStatus = HttpStatus.NOT_FOUND;
        String expectedMessage = "{ \"error\": \"This author is not present in the database\" }";

        assertEquals(expectedStatus, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
    }

    @Test
    public void test() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Author author = new Author();
        author.setFirstName("Jane");
        author.setLastName("Austen");
        author.setId(1l);

        BookDto bookDto = new BookDto();
        bookDto.setTitle("book1");
        bookDto.setCategory(Category.FANTASY);
        bookDto.setAuthor(author);

        ResponseEntity<String> response = restTemplate.postForEntity(POST_BOOK_REQUEST, bookDto, String.class);

        HttpStatus expectedStatus = HttpStatus.NOT_FOUND;
        String expectedMessage = "{ \"error\": \"This author is not present in the database\" }";

        assertEquals(expectedStatus, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());

    }


}