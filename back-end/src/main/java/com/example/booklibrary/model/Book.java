package com.example.booklibrary.model;

import com.example.booklibrary.dto.PlainBookDto;
import com.example.booklibrary.model.enums.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books", uniqueConstraints = {@UniqueConstraint(name = "UniqueTitle", columnNames = {"title"})})
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbn;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author")
    private Author author;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "category")
    private Category category;

    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows = new ArrayList<>();

    public Book(String title, Author author, Category category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public static Book from(PlainBookDto plainBookDto) {
        Book book = new Book();
        book.setIsbn(plainBookDto.getIsbn());
        book.setTitle(plainBookDto.getTitle());
        book.setAuthor(plainBookDto.getAuthor());
        book.setCategory(plainBookDto.getCategory());

        return book;
    }
}
