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
import java.time.LocalDate;

@Entity
@Table(name = "borrows")
@Data
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Column
//    private String borrowDate;
    @Column
    @CreationTimestamp
    private LocalDate borrowDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public static Borrow from(BorrowDto borrowDto) {
        Borrow borrow = new Borrow();
        borrow.setBorrowDate(borrowDto.getBorrowDate());
        // todo set book and user
        return borrow;
    }
}
