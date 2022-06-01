package com.example.booklibrary.dto;

import com.example.booklibrary.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<BorrowDto> borrowDtoList = new ArrayList<>();

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setBorrowDtoList(user.getBorrows().stream().map(BorrowDto::from).collect(Collectors.toList()));

        return userDto;
    }
}
