package com.example.booklibrary.dto;

import com.example.booklibrary.model.Author;
import com.example.booklibrary.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    private String title;
    private Author author;
    private Category category;
}
