package com.example.booklibrary.dto;

import com.example.booklibrary.model.Borrow;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BorrowDtoTest {

    @Test
    public void testFrom() {
        LocalDate date = LocalDate.of(2000, 3, 3);
        Borrow borrow = new Borrow();
        borrow.setBorrowDate(date);

        BorrowDto expected = new BorrowDto();
        expected.setBorrowDate(date);

        BorrowDto actual = BorrowDto.from(borrow);
        assertEquals(expected, actual,
                "Method does not correctly convert object of type Borrow to object of type BorrowDto");
    }

}