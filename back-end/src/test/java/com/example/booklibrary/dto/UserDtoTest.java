package com.example.booklibrary.dto;

import com.example.booklibrary.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    public void testFrom() {
        String firstName = "John";
        String lastName = "Smith";
        String email = "john@gmail.com";

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        UserDto expected = new UserDto();
        expected.setFirstName(firstName);
        expected.setLastName(lastName);
        expected.setEmail(email);

        UserDto actual = UserDto.from(user);
        assertEquals(expected, actual,
                "Method does not correctly convert object of type User to object of type UserDto"
        );
    }
}