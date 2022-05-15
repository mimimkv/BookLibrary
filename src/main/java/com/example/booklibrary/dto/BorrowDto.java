package com.example.booklibrary.dto;

import com.example.booklibrary.model.Borrow;
import lombok.Data;

import java.util.Objects;

@Data
public class BorrowDto {
    private Long id;
    private String borrowDate;
    private PlainUserDto plainUserDto;

    public static BorrowDto from(Borrow borrow) {
        System.out.println(borrow.getBorrowDate());
        BorrowDto borrowDto = new BorrowDto();
        borrowDto.setId(borrow.getId());
        borrowDto.setBorrowDate(borrow.getBorrowDate());
        if (Objects.nonNull(borrow.getUser())) {
            borrowDto.setPlainUserDto(PlainUserDto.from(borrow.getUser()));
        }

        return borrowDto;
    }
}
