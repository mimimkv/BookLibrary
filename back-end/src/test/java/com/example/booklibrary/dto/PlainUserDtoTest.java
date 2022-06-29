package com.example.booklibrary.dto;

import com.example.booklibrary.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlainUserDtoTest {

    @Test
    public void testFrom() {
        String firstName = "John";
        String lastName = "Smith";
        String email = "john@gmail.com";

        PlainUserDto expected = new PlainUserDto();
        expected.setFirstName(firstName);
        expected.setLastName(lastName);
        expected.setEmail(email);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        PlainUserDto actual = PlainUserDto.from(user);
        assertEquals(expected, actual,
            "Method does not correctly convert object of type User to object of type PlainUserDto"
        );
    }
}