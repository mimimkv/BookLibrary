package com.example.booklibrary.dto;

import com.example.booklibrary.model.Author;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.enums.Category;
import lombok.Data;

@Data
public class PlainBookDto {
    private Long isbn;
    private String title;
    private Author author;
    private Category category;

    public static PlainBookDto from(Book book) {
        PlainBookDto plainBookDto = new PlainBookDto();
        plainBookDto.setIsbn(book.getIsbn());
        plainBookDto.setTitle(book.getTitle());
        plainBookDto.setAuthor(book.getAuthor());
        plainBookDto.setCategory(book.getCategory());

        return plainBookDto;
    }
}
