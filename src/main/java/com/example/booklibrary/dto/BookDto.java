package com.example.booklibrary.dto;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private Category category;

    public static Book mapToBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());

        return book;
    }
}
