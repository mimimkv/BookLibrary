package com.example.booklibrary.dto;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.Author;
import com.example.booklibrary.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookDto {
    private Long isbn;
    private String title;
    private Author author;
    private Category category;
    private List<BorrowDto> borrowDtoList = new ArrayList<>();

    public static Book mapToBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());

        return book;
    }

    public static BookDto from(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setCategory(book.getCategory());
        bookDto.setBorrowDtoList(book.getBorrows().stream().map(BorrowDto::from).collect(Collectors.toList()));

        return bookDto;
    }
}
