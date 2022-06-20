package com.example.booklibrary.dto;

import com.example.booklibrary.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotNull
    private String email;
    private List<BorrowDto> borrowDtoList = new ArrayList<>();

    private LocalDate joinedDate;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setJoinedDate(user.getJoinedDate());
        userDto.setBorrowDtoList(user.getBorrows().stream().map(BorrowDto::from).collect(Collectors.toList()));

        return userDto;
    }
}
