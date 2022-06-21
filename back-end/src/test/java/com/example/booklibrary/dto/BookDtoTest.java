package com.example.booklibrary.dto;

import com.example.booklibrary.model.Author;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.enums.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDtoTest {

    @Test
    public void testMapToBook() {
        String title = "book1";
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Smith");

        Book expected = new Book(title, author, Category.FANTASY);
        BookDto bookDto = new BookDto();
        bookDto.setTitle(title);
        bookDto.setAuthor(author);
        bookDto.setCategory(Category.FANTASY);

        Book actual = BookDto.mapToBook(bookDto);
        assertEquals(expected, actual,
                "Method does not convert properly an instance of BookDto to Book object");
    }

    @Test
    public void testFrom() {
        String title = "book1";
        Author author = new Author("name1", "name2");

        Book book = new Book(title, author, Category.FANTASY);
        BookDto expected = new BookDto();
        expected.setTitle(title);
        expected.setAuthor(author);
        expected.setCategory(Category.FANTASY);

        BookDto actual = BookDto.from(book);
        assertEquals(expected, actual,
                "Method does not convert properly an instance of Book to BookDto object");
    }
}