package com.example.booklibrary.dto;

import com.example.booklibrary.model.Author;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.enums.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlainBookDtoTest {

    @Test
    public void testFrom() {
        String title = "book1";
        Author author = new Author("name1", "name2");

        Book book = new Book(title, author, Category.FANTASY);
        PlainBookDto expected = new PlainBookDto();
        expected.setTitle(title);
        expected.setAuthor(author);
        expected.setCategory(Category.FANTASY);
        PlainBookDto actual = PlainBookDto.from(book);

        assertEquals(expected, actual,
            "Method does not correctly convert object of type Book to object of type PlainBookDto");
    }

}