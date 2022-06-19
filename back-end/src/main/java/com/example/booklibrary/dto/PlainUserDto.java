package com.example.booklibrary.dto;

import com.example.booklibrary.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class PlainUserDto {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;

    private LocalDate joinedDate;

    public static PlainUserDto from(User user) {
        PlainUserDto plainUserDto = new PlainUserDto();
        plainUserDto.setId(user.getId());
        plainUserDto.setFirstName(user.getFirstName());
        plainUserDto.setLastName(user.getLastName());
        plainUserDto.setEmail(user.getEmail());
        plainUserDto.setJoinedDate(user.getJoinedDate());

        return  plainUserDto;
    }
}
