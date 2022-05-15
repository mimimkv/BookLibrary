package com.example.booklibrary.controller;

import com.example.booklibrary.dto.BorrowDto;
import com.example.booklibrary.dto.UserDto;
import com.example.booklibrary.exceptions.UserNotFoundException;
import com.example.booklibrary.model.Borrow;
import com.example.booklibrary.model.User;
import com.example.booklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library")
public class UserController {

    private static final String DELETED_LABEL = "deleted";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> usersDtoList = users.stream().map(UserDto::from).collect(Collectors.toList());

        return new ResponseEntity<>(usersDtoList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user;
        try {
            user = userService.getUserById(id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(User.from(userDto));
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);

    }

    @PostMapping("/users/{userId}/borrow/")
    public ResponseEntity<UserDto> borrowBook(@PathVariable Long userId, BorrowDto borrowDto) {
        User user;
        try {
            user = userService.borrowBook(userId, borrowDto);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user;
        try {
            user = userService.updateUser(id, User.from(userDto));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    
    @DeleteMapping("users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put(DELETED_LABEL, Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}