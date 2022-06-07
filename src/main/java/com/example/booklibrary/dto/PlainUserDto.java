package com.example.booklibrary.dto;

import com.example.booklibrary.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class PlainUserDto {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
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
