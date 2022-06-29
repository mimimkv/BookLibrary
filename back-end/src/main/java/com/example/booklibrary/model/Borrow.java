package com.example.booklibrary.model;

import com.example.booklibrary.dto.BorrowDto;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

@Entity
@Table(name = "borrows", uniqueConstraints = {
    @UniqueConstraint(name = "UniqueUserAndBook", columnNames = {"user_id", "book_id"})})
@Data
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @CreationTimestamp
    private LocalDate borrowDate;

    @Column
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public static Borrow from(BorrowDto borrowDto) {
        Borrow borrow = new Borrow();
        borrow.setId(borrowDto.getId());
        borrow.setBorrowDate(borrowDto.getBorrowDate());
        borrow.setReturnDate(borrowDto.getReturnDate());
        borrow.setUser(User.from(borrowDto.getPlainUserDto()));
        borrow.setBook(Book.from(borrowDto.getPlainBookDto()));

        return borrow;
    }
}
