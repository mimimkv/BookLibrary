package com.example.booklibrary.model;

import com.example.booklibrary.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testBorrowAddOneBorrow() {
        LocalDate date = LocalDate.of(2000, 12, 21);
        Borrow borrow = new Borrow();
        borrow.setBorrowDate(date);

        User user = new User();
        user.borrow(borrow);

        List<Borrow> expected = List.of(borrow);
        List<Borrow> actual = user.getBorrows();
        assertIterableEquals(expected, actual,
            "Method borrow() does not add the borrow");
    }

    @Test
    public void testFrom() {
        String firstName = "John";
        String lastName = "Smith";
        String email = "john@abv.bg";

        UserDto userDto = new UserDto();
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);

        User expected = new User();
        expected.setFirstName(firstName);
        expected.setLastName(lastName);
        expected.setEmail(email);

        User actual = User.from(userDto);
        assertEquals(expected, actual,
            "Method does not correctly convert object of type UserDto to object of type User"
        );
    }
}