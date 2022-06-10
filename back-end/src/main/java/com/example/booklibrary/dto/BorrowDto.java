package com.example.booklibrary.dto;

import com.example.booklibrary.model.Borrow;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Data
public class BorrowDto {
    private Long id;
    private LocalDate borrowDate;
    @Valid
    @NotNull
    private PlainUserDto plainUserDto;
    @Valid
    @NotNull
    private PlainBookDto plainBookDto;

    public static BorrowDto from(Borrow borrow) {
        System.out.println(borrow.getBorrowDate());
        BorrowDto borrowDto = new BorrowDto();
        borrowDto.setId(borrow.getId());
        borrowDto.setBorrowDate(borrow.getBorrowDate());
        if (Objects.nonNull(borrow.getUser())) {
            borrowDto.setPlainUserDto(PlainUserDto.from(borrow.getUser()));
        }
        if (Objects.nonNull(borrow.getBook())) {
            borrowDto.setPlainBookDto(PlainBookDto.from(borrow.getBook()));
        }

        return borrowDto;
    }
}
