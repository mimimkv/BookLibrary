package com.example.booklibrary.dto;

import com.example.booklibrary.model.Author;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.enums.Category;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PlainBookDto {
    private Long isbn;
    @NotBlank
    private String title;
    @Valid
    private Author author;
    @NotNull
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
