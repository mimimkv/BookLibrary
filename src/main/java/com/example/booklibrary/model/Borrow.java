package com.example.booklibrary.model;

import com.example.booklibrary.dto.BorrowDto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "borrows")
@Data
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String borrowDate;

    @ManyToOne
    private User user;

    public static Borrow from(BorrowDto borrowDto) {
        Borrow borrow = new Borrow();
        borrow.setBorrowDate(borrowDto.getBorrowDate());
        return borrow;
    }
}
