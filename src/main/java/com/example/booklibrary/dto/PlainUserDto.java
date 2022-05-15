package com.example.booklibrary.dto;

import com.example.booklibrary.model.User;
import lombok.Data;

@Data
public class PlainUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public static PlainUserDto from(User user) {
        PlainUserDto plainUserDto = new PlainUserDto();
        plainUserDto.setId(user.getId());
        plainUserDto.setFirstName(user.getFirstName());
        plainUserDto.setLastName(user.getLastName());
        plainUserDto.setEmail(user.getEmail());

        return  plainUserDto;
    }
}
